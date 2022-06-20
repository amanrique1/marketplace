package com.marketplace.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.exceptions.DAOException;
import com.marketplace.model.Categoria;
import com.marketplace.model.Cotizacion;
import com.marketplace.model.InfoProducto;
import com.marketplace.model.Producto;
import com.marketplace.model.ResponseBody.DarInfoCategorias;
import com.marketplace.model.ResponseBody.DarInfoProductos;
import com.marketplace.model.ResponseBody.ListaCotizaciones;
import com.marketplace.model.ResponseBody.ListaProductos;
import com.marketplace.model.entity.CategoriaEntity;
import com.marketplace.model.entity.CotizacionEntity;
import com.marketplace.model.entity.ImagenEntity;
import com.marketplace.model.entity.ProductoEntity;

@RequestScoped
public class InventarioDAO {

	/**
	 * Manejador de la persistencia
	 */
	@Inject
	EntityManager em;

	/**
	 * Busca el entity completo de un producto
	 * @param id del producto
	 * @return productoEntity
	 */
	public ProductoEntity darProductoEntity(Long id) {
		return em.find(ProductoEntity.class, id);
	}

	/**
	 * Busca los nombres de los productos y los agrega a una lista
	 * @return lista de nombres de los productos
	 */
	public DarInfoProductos darNombresProducto(){
		String queryStr =
				"SELECT NEW com.marketplace.model.InfoProducto(p.id, p.nombre) " +
						"FROM ProductoEntity AS p WHERE p.aprobado=true ORDER BY p.nombre";
		return new DarInfoProductos(em.createQuery(queryStr,InfoProducto.class).getResultList());
	}

	/**
	 * Busca los producto
	 * @param id del producto
	 * @return producto dado el id especificado
	 * @throws DAOException 
	 */
	public Producto darProducto(Long id) throws DAOException{
		String queryStr =
				"SELECT p.aprobado FROM ProductoEntity p WHERE p.id=:id";
		TypedQuery<Boolean> query = em.createQuery(queryStr,Boolean.class);
		query.setParameter("id", id);
		List<Boolean> aprobado = query.getResultList();
		if(aprobado.isEmpty()) {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.producto.no.encontrado", String.class));
		}
		if(aprobado.get(0)) {
			String queryStr2 =
					"SELECT NEW com.marketplace.model.Producto(p.id, p.nombre, p.precioTotal, p.valorImpuesto, p.moneda, p.stock, p.marca, "
							+ "p.modelo, p.peso, p.unidadDePeso, p.anchura, p.altura, p.profundidad, p.unidadDeMedida,i.imagen,i.formatoImagen)"
							+ " FROM ProductoEntity p, ImagenEntity i WHERE p.id=:id";
			TypedQuery<Producto> query2 = em.createQuery(queryStr2,Producto.class);
			query2.setParameter("id", id);
			return query2.getSingleResult();
		}else {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.producto.no.aprobado", String.class));
		}
	}

	/**
	 * Busca la cotizacion
	 * @param id de la cotizacion
	 * @return cotizacion dado el id especificado
	 */
	public Cotizacion darCotizacion(Long id){
		String queryStr =
				"SELECT NEW com.marketplace.model.Cotizacion(c.nombre, c.productoBase,c.comentarios,c.linkDeReferencia,c.adjuntoDeReferencia, c.marca, c.modelo, c.cantidadProducto, c.categoria.nombre, c.proveedor.nombre, c.imagenReferencia.imagen, c.imagenReferencia.formatoImagen)"
						+ " FROM CotizacionEntity c WHERE c.id=:id";
		TypedQuery<Cotizacion> query = em.createQuery(queryStr,Cotizacion.class);
		query.setParameter("id", id);
		List<Cotizacion> cotizaciones = query.getResultList();
		return cotizaciones.isEmpty() ? null : cotizaciones.get(0);
	}

	/**
	 * Busca todas las cotizaciones
	 * @return cotizaciones
	 */
	public ListaCotizaciones darCotizaciones(Integer posInicial, Integer limite){
		String queryStr =
				"SELECT NEW com.marketplace.model.Cotizacion(c.nombre, c.productoBase,c.comentarios,c.linkDeReferencia,c.adjuntoDeReferencia, c.marca, c.modelo, c.cantidadProducto, c.categoria.nombre, c.proveedor.nombre, c.imagenReferencia.imagen, c.imagenReferencia.formatoImagen)"
						+ " FROM CotizacionEntity c";
		TypedQuery<Cotizacion> query = em.createQuery(queryStr,Cotizacion.class);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		return new ListaCotizaciones(query.getResultList());
	}

	/**
	 * Busca todas las cotizaciones de una categoria
	 * @param id de la categoria
	 * @return cotizaciones
	 * @throws DAOException si no existe la categoria
	 */
	public ListaCotizaciones darCotizacionesCategoria(Long idCategoria,Integer posInicial, Integer limite) throws DAOException{
		String queryStr =
				"SELECT NEW com.marketplace.model.Cotizacion(c.nombre, c.productoBase,c.comentarios,c.linkDeReferencia,c.adjuntoDeReferencia, c.marca, c.modelo, c.cantidadProducto, c.categoria.nombre, c.proveedor.nombre, c.imagenReferencia.imagen, c.imagenReferencia.formatoImagen)"
						+ " FROM CotizacionEntity c WHERE c.categoria.id=:id";
		TypedQuery<Cotizacion> query = em.createQuery(queryStr,Cotizacion.class);
		query.setParameter("id", idCategoria);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<Cotizacion> cotizaciones = query.getResultList();
		if(cotizaciones.isEmpty() && !existeCategoria(idCategoria)) {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.categoria.inexistente", String.class));
		}
		return new ListaCotizaciones(cotizaciones);
	}

	/**
	 * Verifica si existe la categoria a partir de un id recibido
	 * @param idCategoria a verificar
	 * @return boolean indicanto si existe la categoria
	 */
	public Boolean existeCategoria(Long idCategoria) {
		String queryStr =
				"SELECT c.nombre FROM CategoriaEntity c WHERE c.id=:id";
		TypedQuery<String> query = em.createQuery(queryStr,String.class);
		query.setParameter("id", idCategoria);
		if(query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Busca los producto a partir de la categoria
	 * @param id de la categoria
	 * @return productos cuya categoria es la recibida por parametro
	 * @throws DAOException 
	 */
	public ListaProductos darProductosPorCategoria(Long idCategoria,Integer posInicial, Integer limite) throws DAOException{
		String queryStr =
				"SELECT NEW com.marketplace.model.Producto(p.id, p.nombre, p.precioTotal, p.valorImpuesto, p.moneda, p.stock, p.marca, "
						+ "p.modelo, p.peso, p.unidadDePeso, p.anchura, p.altura, p.profundidad, p.unidadDeMedida,i.imagen,i.formatoImagen)"
						+ " FROM ProductoEntity p, ImagenEntity i, CategoriaEntity c WHERE c.id=:id AND p.aprobado=:aprobado ORDER BY p.nombre DESC";
		TypedQuery<Producto> query = em.createQuery(queryStr,Producto.class);
		query.setParameter("id", idCategoria);
		query.setParameter("aprobado", true);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<Producto> prods = query.getResultList();
		if(prods.isEmpty() && !existeCategoria(idCategoria)) {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.categoria.inexistente", String.class));
		}
		return new ListaProductos(prods);
	}

	/**
	 * Busca los producto a partir del email del proveedor
	 * @param email del proveedor a buscar
	 * @return productos cuya categoria es la recibida por parametro
	 * @throws DAOException 
	 */
	public ListaProductos darProductosPorProveedor(String email,Integer posInicial, Integer limite) throws DAOException{
		String queryStr =
				"SELECT NEW com.marketplace.model.Producto(p.id, p.nombre, p.precioTotal, p.valorImpuesto, p.moneda, p.stock, p.marca, "
						+ "p.modelo, p.peso, p.unidadDePeso, p.anchura, p.altura, p.profundidad, p.unidadDeMedida,p.aprobado,i.imagen,i.formatoImagen)"
						+ " FROM ProductoEntity p, ImagenEntity i, CategoriaEntity c, ProveedorEntity prov WHERE prov.email=:email AND p.aprobado=:aprobado ORDER BY p.nombre DESC";
		TypedQuery<Producto> query = em.createQuery(queryStr,Producto.class);
		query.setParameter("email", email);
		query.setParameter("aprobado", true);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<Producto> prods = query.getResultList();
		Query query2 = em.createQuery("SELECT p.nombre FROM ProveedorEntity p WHERE p.email=:email");
		query2.setParameter("email", email);
		if(prods.isEmpty()) {
			if( query2.getResultList().isEmpty()) {
				throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.categoria.inexistente", String.class));
			}
		}
		return new ListaProductos(prods);
	}

	/**
	 * Busca los producto a partir de un texto
	 * @param textoProducto texto al interior del nombre
	 * @return productos cuya nombre contenga el texto
	 */
	public ListaProductos darProductosPorPatron(String textoProducto,Integer posInicial, Integer limite){
		String queryStr =
				"SELECT NEW com.marketplace.model.Producto(p.id, p.nombre, p.precioTotal, p.valorImpuesto, p.moneda, p.stock, p.marca, "
						+ "p.modelo, p.peso, p.unidadDePeso, p.anchura, p.altura, p.profundidad, p.unidadDeMedida,i.imagen,i.formatoImagen)"
						+ " FROM ProductoEntity p, ImagenEntity i WHERE UPPER(p.nombre) LIKE UPPER('%"+textoProducto+"%') ORDER BY p.nombre DESC";
		TypedQuery<Producto> query = em.createQuery(queryStr,Producto.class);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		return new ListaProductos(query.getResultList());
	}

	/**
	 * Busca los nombres de las categorias y los agrega a una lista
	 * @return lista de nombres de los productos
	 */
	public DarInfoCategorias darNombresCategorias(){
		String queryStr =
				"SELECT NEW com.marketplace.model.Categoria(c.id, c.nombre, c.icono) " +
						"FROM CategoriaEntity AS c";
		List<Categoria> categorias= em.createQuery(queryStr,Categoria.class).getResultList();
		DarInfoCategorias listaCategorias = new DarInfoCategorias();
		listaCategorias.setcategorias(categorias);
		return listaCategorias;
	}

	/**
	 * Agrega una producto a la base de datos
	 * @param producto a agregar
	 * @throws DAOException si hay un error guardando el producto
	 */
	@Transactional
	public void crearProducto (ProductoEntity producto) throws DAOException {
		try {
			em.persist(producto);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Agrega una imagen a la base de datos
	 * @param producto a agregar
	 * @throws DAOException si hay un error guardando la imagen
	 */
	@Transactional
	public void guardarImagen(ImagenEntity imagen) throws DAOException {
		try {
			em.persist(imagen);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Busca la categoria a traves de su id
	 * @param id de la categoria
	 * @return categoria
	 */
	public CategoriaEntity buscarCategoria(Long id){
		return em.find(CategoriaEntity.class, id);
	}

	/**
	 * Agrega un producto a la base de datos
	 * @param categoria a agregar
	 * @throws DAOException si hay un error guardando el producto
	 */
	@Transactional
	public void agregarCategoria(CategoriaEntity categoria) throws DAOException {
		try {
			em.persist(categoria);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Agrega la cotizacion a la BD
	 * @param cotizacion a persistir
	 * @throws DAOException si hay un error guardando el producto
	 */
	@Transactional
	public void crearCotizacion(CotizacionEntity cotizacion) throws DAOException{
		try {
			em.persist(cotizacion);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * Actualiza el valor del producto y le cambia la propiedad de listar
	 * @param idProducto
	 * @param valorFee
	 * @throws DAOException si hay un error actualizando el producto
	 */
	@Transactional
	public void publicarProducto(Long idProducto,Double valorFee) throws DAOException{
		try {
			ProductoEntity prod = em.find(ProductoEntity.class, idProducto);
			prod.setValorFee(valorFee);
			prod.setAprobado(true);
			prod.setPrecioTotal((prod.getPrecioTotal()+valorFee));
			em.merge(prod);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
}
