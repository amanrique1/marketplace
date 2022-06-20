package com.marketplace.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.exceptions.DAOException;
import com.marketplace.model.CambioEstado;
import com.marketplace.model.Cotizacion;
import com.marketplace.model.OrdenDeCompraProveedor;
import com.marketplace.model.Producto;
import com.marketplace.model.ProductoOrdenProveedor;
import com.marketplace.model.Ubicacion;
import com.marketplace.model.ResponseBody.ListaCotizaciones;
import com.marketplace.model.ResponseBody.ListaOrdenesDeCompraProveedor;
import com.marketplace.model.ResponseBody.ListaProductos;

@RequestScoped
public class ProveedorDAO {

	/**
	 * Manejador de la persistencia
	 */
	@Inject
	EntityManager em;

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
						+ " FROM ProductoEntity p, ImagenEntity i, CategoriaEntity c, ProveedorEntity prov WHERE prov.email=:email ORDER BY p.nombre DESC";
		TypedQuery<Producto> query = em.createQuery(queryStr,Producto.class);
		query.setParameter("email", email);
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
	 * Busca todas las cotizaciones de un proveedor
	 * @param email del proveedor
	 * @return cotizaciones
	 */
	public ListaCotizaciones darCotizacionesProveedor(String email,Integer posInicial, Integer limite){
		String queryStr =
				"SELECT NEW com.marketplace.model.Cotizacion(c.nombre, c.productoBase,c.comentarios,c.linkDeReferencia,c.adjuntoDeReferencia, c.marca, c.modelo, c.cantidadProducto, cat.nombre, prov.nombre, i.imagen, i.formatoImagen)"
						+ " FROM CotizacionEntity c, ImagenEntity i, CategoriaEntity cat, ProveedorEntity prov WHERE prov.email=:email ORDER BY c.nombre DESC";
		TypedQuery<Cotizacion> query = em.createQuery(queryStr,Cotizacion.class);
		query.setParameter("email", email);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		return new ListaCotizaciones(query.getResultList());	
	}

	/**
	 * Busca las ordenes de compra de una persona
	 * @param email de la persona
	 * @param posInicial de busqueda
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws DAOException
	 */
	public ListaOrdenesDeCompraProveedor darOrdenDeCompraProveedor(String email,Integer posInicial,Integer limite) throws DAOException {
		try {
			String queryStr =
					"SELECT NEW com.marketplace.model.OrdenDeCompraProveedor(o.id, o.fecha, o.fechaUltimaModificacion,o.estado) FROM OrdenDeCompraEntity o JOIN o.productosOrden po JOIN po.producto prod JOIN prod.proveedor p WHERE p.email=:email  ORDER BY o.fechaUltimaModificacion DESC";
			TypedQuery<OrdenDeCompraProveedor> query = em.createQuery(queryStr,OrdenDeCompraProveedor.class);
			query.setParameter("email", email);
			query.setFirstResult(posInicial);
			query.setMaxResults(limite);
			List<OrdenDeCompraProveedor> ordenes = query.getResultList();
			Ubicacion ubicacion = null;
			for (int i = 0; i < ordenes.size(); i++) {
				queryStr =
						"SELECT NEW com.marketplace.model.Ubicacion(u.pais,u.estado,u.ciudad,u.direccion,u.detalles) FROM OrdenDeCompraEntity o JOIN o.ubicacion u WHERE o.id=:id";
				TypedQuery<Ubicacion> query2 = em.createQuery(queryStr,Ubicacion.class);
				query2.setParameter("id", ordenes.get(i).getId());
				List<Ubicacion> ubicaciones = query2.getResultList();
				ubicacion = ubicaciones.isEmpty() ? null : ubicaciones.get(0);
				if(ubicacion == null) {
					throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.ubicacion.ordenDeCompra.inexistente", String.class));
				}
				ordenes.get(i).setUbicacion(ubicacion);
				queryStr =
						"SELECT NEW com.marketplace.model.ProductoOrdenProveedor(prod.id, prod.nombre,prod.imagen.imagen, prod.imagen.formatoImagen,po.cantidadProducto,po.detalles) FROM OrdenDeCompraEntity o JOIN o.productosOrden po JOIN po.producto prod JOIN prod.proveedor p WHERE p.email=:email AND o.id=:id";
				TypedQuery<ProductoOrdenProveedor> query3 = em.createQuery(queryStr,ProductoOrdenProveedor.class);
				query3.setParameter("id", ordenes.get(i).getId());
				query3.setParameter("email", email);
				ordenes.get(i).setProductosOrden(query3.getResultList());
				queryStr =
						"SELECT NEW com.marketplace.model.CambioEstado(c.estado,c.fechaModificacion,p.nombre) FROM OrdenDeCompraEntity o JOIN o.cambioEstado c JOIN o.persona p WHERE o.id=:id";
				TypedQuery<CambioEstado> query4 = em.createQuery(queryStr,CambioEstado.class);
				query4.setParameter("id", ordenes.get(i).getId());
				ordenes.get(i).setCambiosEstado(query4.getResultList());
			}
			return new ListaOrdenesDeCompraProveedor(ordenes);
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}
}
