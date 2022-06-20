package com.marketplace.ejb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.DAO.InventarioDAO;
import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.exceptions.DAOException;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.exceptions.WSException;
import com.marketplace.model.Cotizacion;
import com.marketplace.model.FormatoImagen;
import com.marketplace.model.Producto;
import com.marketplace.model.BodyParams.AgregarCategoriaParams;
import com.marketplace.model.BodyParams.AgregarCotizacionParams;
import com.marketplace.model.BodyParams.AgregarProductoParams;
import com.marketplace.model.BodyParams.PublicarProductoParams;
import com.marketplace.model.ResponseBody.DarInfoCategorias;
import com.marketplace.model.ResponseBody.DarInfoProductos;
import com.marketplace.model.ResponseBody.IdObject;
import com.marketplace.model.ResponseBody.ListaCotizaciones;
import com.marketplace.model.ResponseBody.ListaProductos;
import com.marketplace.model.entity.CategoriaEntity;
import com.marketplace.model.entity.CotizacionEntity;
import com.marketplace.model.entity.ImagenEntity;
import com.marketplace.model.entity.PersonaEntity;
import com.marketplace.model.entity.ProductoEntity;
import com.marketplace.model.entity.ProveedorEntity;

@RequestScoped
public class InventarioService {

	/**
	 * Objeto encargado de la persistencia
	 */
	@Inject
	InventarioDAO inventarioDAO;

	/**
	 * Objeto para comunicarse con la persistencia relacionada a usuarios
	 */
	@Inject
	UsuarioDAO usuarioDAO;

	/**
	 * Busca los nombres y id en la persistencia
	 * @return nombres productos
	 */
	public DarInfoProductos darInfoProductos() {
		return inventarioDAO.darNombresProducto();
	}

	/**
	 * Busca en la persistencia un producto a partir de su id
	 * @return lista de productos
	 * @throws ServiceException 
	 */
	public Producto darProducto(Long idProducto) throws ServiceException {
		try {
			Producto prod = inventarioDAO.darProducto(idProducto);
			if(prod != null) {
				return prod;
			}else {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.producto.no.encontrado", String.class));
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca en la persistencia un producto a partir de la categoria
	 * @param idCategoria a buscar
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de productos
	 * @throws ServiceException si no existe la categoria
	 */
	public ListaProductos darProductoCategoria(Long idCategoria,Integer posInicial, Integer limite) throws ServiceException {
		try {
			return inventarioDAO.darProductosPorCategoria(idCategoria, posInicial,limite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca los producto a partir de un texto
	 * @param patronProducto texto al interior del nombre
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return productos cuya nombre contenga el texto
	 */
	public ListaProductos darProductosPorPatron(String textoProducto,Integer posInicial, Integer limite){
		return inventarioDAO.darProductosPorPatron(textoProducto,posInicial, limite);
	}

	/**
	 * Busca los nombres y id en la persistencia
	 * @return nombres categorias
	 */
	public DarInfoCategorias darInfoCategorias() {
		return inventarioDAO.darNombresCategorias();
	}

	/**
	 * Persiste la producto en la base de datos
	 * @param producto a agregar a la BD
	 * @return id del producto creado
	 * @throws WSException si hay problemas agregando a la producto
	 */
	public IdObject crearProducto (AgregarProductoParams producto, String emailProveedor) throws ServiceException {
		try {
			ProveedorEntity proveedor = usuarioDAO.buscarProveedor(emailProveedor);
			if(proveedor == null) {
				String proovedorInexistente = ConfigProvider.getConfig().getValue("mensaje.proveedor.inexistente", String.class);
				throw new ServiceException(proovedorInexistente,Response.Status.BAD_REQUEST);
			}
			CategoriaEntity categoria = inventarioDAO.buscarCategoria(producto.getCategoria());
			if(categoria == null) {
				String categoriaInexistente = ConfigProvider.getConfig().getValue("mensaje.categoria.inexistente", String.class);
				throw new ServiceException(categoriaInexistente,Response.Status.BAD_REQUEST);
			}
			ProductoEntity prodEntity = new ProductoEntity();
			if(producto.getImagen() != null) {
				ImagenEntity imagen = new ImagenEntity();
				FormatoImagen formatoImg= FormatoImagen.valueOf(producto.getImagen().get().getFormato());
				imagen.setFormatoImagen(formatoImg);
				imagen.setImagen(producto.getImagen().get().getImagen());
				inventarioDAO.guardarImagen(imagen);
				prodEntity.setImagen(imagen);
			}
			prodEntity.setProveedor(proveedor);
			prodEntity.setCategoria(categoria);
			prodEntity.setAprobado(false);
			prodEntity.setMarca(producto.getMarca());
			prodEntity.setModelo(producto.getModelo());
			prodEntity.setMoneda(producto.getMoneda());
			prodEntity.setNombre(producto.getNombre());
			prodEntity.setPeso(producto.getPeso());
			prodEntity.setPrecioTotal(producto.getPrecioTotal());
			prodEntity.setStock(producto.getStock());
			prodEntity.setAnchura(producto.getMedida().getAnchura());
			prodEntity.setAltura(producto.getMedida().getAltura());
			prodEntity.setProfundidad(producto.getMedida().getProfundidad());
			prodEntity.setUnidadDeMedida(producto.getMedida().getUnidadDeMedida());
			prodEntity.setUnidadDePeso(producto.getUnidadDePeso());
			prodEntity.setValorImpuesto(producto.getValorImpuesto());
			inventarioDAO.crearProducto(prodEntity);
			return new IdObject(prodEntity.getId());
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Guarda la cotizacion en la BD
	 * @param cotizacion a persistir
	 * @param email de la persona que va a crear la cotizacion
	 * @return id de la cotizacion
	 * @throws ServiceException si no existe persona, categoria o proveedor
	 */
	public IdObject crearCotizacion(AgregarCotizacionParams cotizacion, String email) throws ServiceException {
		try {
			PersonaEntity persona = usuarioDAO.buscarPersona(email);
			if(persona == null) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.persona.inexistente", String.class),Response.Status.BAD_REQUEST);
			}
			CategoriaEntity categoria = inventarioDAO.buscarCategoria(cotizacion.getCategoria());
			if(categoria == null) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.categoria.inexistente", String.class),Response.Status.BAD_REQUEST);
			}
			ProveedorEntity proveedor = usuarioDAO.buscarProveedor(cotizacion.getProveedor());
			if(proveedor == null) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.proveedor.inexistente", String.class),Response.Status.BAD_REQUEST);
			}

			CotizacionEntity cotizacionEnt = new CotizacionEntity();
			if(cotizacion.getAdjuntoDeReferencia() != null) {
				cotizacionEnt.setAdjuntoDeReferencia(cotizacion.getAdjuntoDeReferencia().get());
			}
			if(cotizacion.getComentarios() != null) {
				cotizacionEnt.setComentarios(cotizacion.getComentarios().get());
			}
			if(cotizacion.getMarca() != null) {
				cotizacionEnt.setComentarios(cotizacion.getMarca().get());
			}
			if(cotizacion.getImagen() != null) {
				ImagenEntity imagen = new ImagenEntity();
				FormatoImagen formatoImg= FormatoImagen.valueOf(cotizacion.getImagen().get().getFormato());
				imagen.setFormatoImagen(formatoImg);
				imagen.setImagen(cotizacion.getImagen().get().getImagen());
				inventarioDAO.guardarImagen(imagen);
				cotizacionEnt.setImagenReferencia(imagen);
			}
			if(cotizacion.getLinkDeReferencia() != null) {
				cotizacionEnt.setLinkDeReferencia(cotizacion.getLinkDeReferencia().get());
			}
			if(cotizacion.getProductoBase() != null) {
				cotizacionEnt.setProductoBase(cotizacion.getProductoBase().get());
			}
			cotizacionEnt.setNombre(cotizacion.getNombre());
			cotizacionEnt.setCategoria(categoria);
			cotizacionEnt.setProveedor(proveedor);
			cotizacionEnt.setPersona(persona);
			inventarioDAO.crearCotizacion(cotizacionEnt);
			return new IdObject(cotizacionEnt.getId());
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * Busca en la persistencia los productos a partir de un proveedor
	 * @param emailProveedor a buscar
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de cotizaciones
	 * @throws ServiceException si no existe la categoria
	 */
	public ListaProductos darProductosProveedor(String emailProveedor,Integer posInicial, Integer limite) throws ServiceException {
		try {
			return inventarioDAO.darProductosPorProveedor(emailProveedor, posInicial,limite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca una cotizacion a partir de un id
	 * @param idCotizacion
	 * @return Cotizacion
	 * @throws ServiceException si no existe la cotizacion
	 */
	public Cotizacion darCotizacion(Long idCotizacion) throws ServiceException {
		Cotizacion cotizacion =inventarioDAO.darCotizacion(idCotizacion);
		if(cotizacion == null) {
			throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.cotizacion.inexistente", String.class),Response.Status.BAD_REQUEST);
		}
		return cotizacion;
	}

	/**
	 * Agrega la categoria a la BD
	 * @param categoria que se va a agregar
	 * @return id de la categoria creada
	 * @throws WSException si hay problemas agregando a la producto
	 */
	public IdObject agregarCategoria(AgregarCategoriaParams categoria) throws ServiceException {
		try {
			CategoriaEntity categoriaEnt = new CategoriaEntity();
			categoriaEnt.setNombre(categoria.getNombre());
			categoriaEnt.setIcono(categoria.getIcono());
			inventarioDAO.agregarCategoria(categoriaEnt);
			return new IdObject(categoriaEnt.getId());
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Manda la informacion a la persistencia para publicar un producto
	 * @param idProducto producto a actualizar
	 * @param valorFeeParam objeto con comision por venta del objeto
	 * @throws ServiceException si hay problemas actualizando la info del producto
	 */
	public void publicarProducto(Long idProducto,PublicarProductoParams valorFeeParam) throws ServiceException {
		try {
			inventarioDAO.publicarProducto(idProducto, valorFeeParam.getValorFee());
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Busca en la persistencia unas cotizaciones a partir de la categoria
	 * @param idCategoria a buscar
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de cotizaciones
	 * @throws ServiceException si no existe la categoria
	 */
	public ListaCotizaciones darCorizacionesCategoria(Long idCategoria,Integer posInicial, Integer limite) throws ServiceException {
		try {
			return inventarioDAO.darCotizacionesCategoria(idCategoria, posInicial,limite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Busca en la persistencia las cotizaciones pertenecientes a una categoria
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de cotizaciones
	 * @throws ServiceException si no existe la categoria
	 */
	public ListaCotizaciones darCotizaciones(Integer posInicial, Integer limite) throws ServiceException {
		return inventarioDAO.darCotizaciones(posInicial,limite);
	}
}