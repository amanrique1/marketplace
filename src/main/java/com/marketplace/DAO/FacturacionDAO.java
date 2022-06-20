package com.marketplace.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.exceptions.DAOException;
import com.marketplace.model.CambioEstado;
import com.marketplace.model.EstadoPedido;
import com.marketplace.model.OrdenDeCompra;
import com.marketplace.model.ProductoOrden;
import com.marketplace.model.ProductoOrdenDetalle;
import com.marketplace.model.Ubicacion;
import com.marketplace.model.ResponseBody.ListaOrdenesDeCompraResumen;
import com.marketplace.model.ResponseBody.OrdenDeCompraDetalle;
import com.marketplace.model.entity.CambioEstadoOrdenDeCompraEntity;
import com.marketplace.model.entity.OrdenDeCompraEntity;
import com.marketplace.model.entity.ProductoOrdenEntity;
import com.marketplace.model.entity.UbicacionEntity;

@RequestScoped
public class FacturacionDAO {

	/**
	 * Manejador de la persistencia
	 */
	@Inject
	EntityManager em;

	/**
	 * Agrage el objeto de orden de compra a la BD
	 * @param ordenDeCompra
	 * @throws DAOException si hay algun problema guardando el objeto
	 */
	@Transactional
	public void crearOrdenDeCompra(OrdenDeCompraEntity ordenDeCompra) throws DAOException {
		try {
			em.persist(ordenDeCompra);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Guarda el orden producto en la BD
	 * @param prodOrden a agregar
	 * @throws DAOException si hay algun problema persistiendo el objeto
	 */
	@Transactional
	public void agregarProductoOrden(ProductoOrdenEntity prodOrden) throws DAOException{
		try {
			em.persist(prodOrden);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Guarda la ubicacion en la BD
	 * @param ubicacion a persistir
	 * @throws DAOException si hay algun problema persistiendo el objeto
	 */
	@Transactional
	public void guardarUbicacion(UbicacionEntity ubicacion) throws DAOException{
		try {
			em.persist(ubicacion);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Guarda el nuevo estado en la BD
	 * @param modEstado a persistir
	 * @throws DAOException si hay algun problema persistiendo el objeto
	 */
	@Transactional
	public void crearModificacionEstado(CambioEstadoOrdenDeCompraEntity modEstado) throws DAOException{
		try {
			em.persist(modEstado);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * Busca la informacion de la orden a partir del id
	 * @param idOrden
	 * @return ordenDeCompra
	 */
	public OrdenDeCompraDetalle darOrdenDeCompra(Long idOrden) throws DAOException {
		String queryStr =
				"SELECT NEW com.marketplace.model.ResponseBody.OrdenDeCompraDetalle(o.id, o.fecha, o.fechaUltimaModificacion,o.estado) FROM OrdenDeCompraEntity o WHERE o.id=:id";
		TypedQuery<OrdenDeCompraDetalle> query = em.createQuery(queryStr,OrdenDeCompraDetalle.class);
		query.setParameter("id", idOrden);
		List<OrdenDeCompraDetalle> ordenes = query.getResultList();
		OrdenDeCompraDetalle orden = ordenes.isEmpty() ? null : ordenes.get(0);
		if(orden == null) {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.ordenDeCompra.inexistente", String.class));
		}
		queryStr =
				"SELECT NEW com.marketplace.model.Ubicacion(u.pais,u.estado,u.ciudad,u.direccion,u.detalles) FROM OrdenDeCompraEntity o JOIN o.ubicacion u WHERE o.id=:id";
		TypedQuery<Ubicacion> query2 = em.createQuery(queryStr,Ubicacion.class);
		query2.setParameter("id", idOrden);
		List<Ubicacion> ubicaciones = query2.getResultList();
		Ubicacion ubicacion = ubicaciones.isEmpty() ? null : ubicaciones.get(0);
		if(ubicacion == null) {
			throw new DAOException(ConfigProvider.getConfig().getValue("mensaje.ubicacion.ordenDeCompra.inexistente", String.class));
		}
		orden.setUbicacion(ubicacion);
		queryStr =
				"SELECT NEW com.marketplace.model.ProductoOrdenDetalle(p.producto.id, p.producto.nombre, p.producto.precioTotal, p.producto.valorImpuesto, p.producto.moneda, p.producto.stock, p.producto.marca, "
						+ "p.producto.modelo, p.producto.peso, p.producto.unidadDePeso, p.producto.anchura, p.producto.altura, p.producto.profundidad, p.producto.unidadDeMedida,p.producto.imagen.imagen,p.producto.imagen.formatoImagen,p.cantidadProducto,p.detalles,p.valorTotal,p.unidadMonetaria) "
						+ "FROM OrdenDeCompraEntity o JOIN o.productosOrden p WHERE o.id=:id";
		TypedQuery<ProductoOrdenDetalle> query3 = em.createQuery(queryStr,ProductoOrdenDetalle.class);
		query3.setParameter("id", idOrden);
		orden.setProductosOrden(query3.getResultList());
		queryStr =
				"SELECT NEW com.marketplace.model.CambioEstado(c.estado,c.fechaModificacion,p.nombre) FROM OrdenDeCompraEntity o JOIN o.cambioEstado c JOIN o.persona p WHERE o.id=:id";
		TypedQuery<CambioEstado> query4 = em.createQuery(queryStr,CambioEstado.class);
		query4.setParameter("id", idOrden);
		orden.setCambiosEstado(query4.getResultList());
		return orden;
	}

	/**
	 * Busca las ordenes de compra de una persona
	 * @param email de la persona
	 * @param posInicial de busqueda
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws DAOException
	 */
	public ListaOrdenesDeCompraResumen darOrdenDeCompraPersona(String email,Integer posInicial,Integer limite) throws DAOException {
		String queryStr =
				"SELECT NEW com.marketplace.model.OrdenDeCompra(o.id, o.fecha, o.fechaUltimaModificacion,o.estado) FROM OrdenDeCompraEntity o JOIN o.persona p WHERE p.email=:email  ORDER BY o.fechaUltimaModificacion DESC";
		TypedQuery<OrdenDeCompra> query = em.createQuery(queryStr,OrdenDeCompra.class);
		query.setParameter("email", email);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<OrdenDeCompra> ordenes = query.getResultList();
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
					"SELECT NEW com.marketplace.model.ProductoOrden(p.producto.id, p.producto.nombre, p.valorTotal,p.unidadMonetaria,p.producto.imagen.imagen,p.producto.imagen.formatoImagen,p.cantidadProducto,p.detalles) FROM OrdenDeCompraEntity o JOIN o.productosOrden p WHERE o.id=:id";
			TypedQuery<ProductoOrden> query3 = em.createQuery(queryStr,ProductoOrden.class);
			query3.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setProductosOrden(query3.getResultList());
			queryStr =
					"SELECT NEW com.marketplace.model.CambioEstado(c.estado,c.fechaModificacion,p.nombre) FROM OrdenDeCompraEntity o JOIN o.cambioEstado c JOIN o.persona p WHERE o.id=:id";
			TypedQuery<CambioEstado> query4 = em.createQuery(queryStr,CambioEstado.class);
			query4.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setCambiosEstado(query4.getResultList());
		}
		return new ListaOrdenesDeCompraResumen(ordenes);
	}
	
	/**
	 * Busca las ordenes de compra de una persona en un estado especifico
	 * @param email de la persona
	 * @param estado de la orden de compra
	 * @param posInicial de busqueda
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws DAOException
	 */
	public ListaOrdenesDeCompraResumen darOrdenDeCompraPersonaEstado(String email,EstadoPedido estado,Integer posInicial,Integer limite) throws DAOException {
		String queryStr =
				"SELECT NEW com.marketplace.model.OrdenDeCompra(o.id, o.fecha, o.fechaUltimaModificacion,o.estado) FROM OrdenDeCompraEntity o JOIN o.persona p WHERE p.email=:email AND o.estado=:estado ORDER BY o.fechaUltimaModificacion DESC";
		TypedQuery<OrdenDeCompra> query = em.createQuery(queryStr,OrdenDeCompra.class);
		query.setParameter("email", email);
		query.setParameter("estado", estado);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<OrdenDeCompra> ordenes = query.getResultList();
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
					"SELECT NEW com.marketplace.model.ProductoOrden(p.producto.id, p.producto.nombre, p.valorTotal,p.unidadMonetaria,p.producto.imagen.imagen,p.producto.imagen.formatoImagen,p.cantidadProducto,p.detalles) FROM OrdenDeCompraEntity o JOIN o.productosOrden p WHERE o.id=:id";
			TypedQuery<ProductoOrden> query3 = em.createQuery(queryStr,ProductoOrden.class);
			query3.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setProductosOrden(query3.getResultList());
			queryStr =
					"SELECT NEW com.marketplace.model.CambioEstado(c.estado,c.fechaModificacion,p.nombre) FROM OrdenDeCompraEntity o JOIN o.cambioEstado c JOIN o.persona p WHERE o.id=:id";
			TypedQuery<CambioEstado> query4 = em.createQuery(queryStr,CambioEstado.class);
			query4.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setCambiosEstado(query4.getResultList());
		}
		return new ListaOrdenesDeCompraResumen(ordenes);
	}
	
	/**
	 * Busca las ordenes de compra de una persona en un estado especifico
	 * @param email de la persona
	 * @param estado de la orden de compra
	 * @param posInicial de busqueda
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws DAOException
	 */
	public ListaOrdenesDeCompraResumen darOrdenDeCompraEstado(EstadoPedido estado,Integer posInicial,Integer limite) throws DAOException {
		String queryStr =
				"SELECT NEW com.marketplace.model.OrdenDeCompra(o.id, o.fecha, o.fechaUltimaModificacion,o.estado) FROM OrdenDeCompraEntity o WHERE o.estado=:estado ORDER BY o.fechaUltimaModificacion DESC";
		TypedQuery<OrdenDeCompra> query = em.createQuery(queryStr,OrdenDeCompra.class);
		query.setParameter("estado", estado);
		query.setFirstResult(posInicial);
		query.setMaxResults(limite);
		List<OrdenDeCompra> ordenes = query.getResultList();
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
					"SELECT NEW com.marketplace.model.ProductoOrden(p.producto.id, p.producto.nombre, p.valorTotal,p.unidadMonetaria,p.producto.imagen.imagen,p.producto.imagen.formatoImagen,p.cantidadProducto,p.detalles) FROM OrdenDeCompraEntity o JOIN o.productosOrden p WHERE o.id=:id";
			TypedQuery<ProductoOrden> query3 = em.createQuery(queryStr,ProductoOrden.class);
			query3.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setProductosOrden(query3.getResultList());
			queryStr =
					"SELECT NEW com.marketplace.model.CambioEstado(c.estado,c.fechaModificacion,p.nombre) FROM OrdenDeCompraEntity o JOIN o.cambioEstado c JOIN o.persona p WHERE o.id=:id";
			TypedQuery<CambioEstado> query4 = em.createQuery(queryStr,CambioEstado.class);
			query4.setParameter("id", ordenes.get(i).getId());
			ordenes.get(i).setCambiosEstado(query4.getResultList());
		}
		return new ListaOrdenesDeCompraResumen(ordenes);
	}
	
	/**
	 * Busca el entity de orden de compra a partir de su id
	 * @param id de la orden de compra
	 * @return OrdenDeCompraEntity
	 */
	public OrdenDeCompraEntity darOrdenDeCompraEntity(Long id) {
		return em.find(OrdenDeCompraEntity.class, id);
	}
	
	/**
	 * Busca el entity de producto a partir de su id
	 * @param id del producto orden
	 * @return ProductoOrdenEntity
	 */
	public ProductoOrdenEntity darProductoOrden(Long id) {
		return em.find(ProductoOrdenEntity.class, id);
	}
	
	/**
	 * Actualiza la informacion de una orden de compra
	 * @param ordenDeCompra a actualizar
	 * @throws DAOException se hay problemas 
	 */
	@Transactional
	public void actualizarOrdenDeCompra(OrdenDeCompraEntity ordenDeCompra) throws DAOException {
		try {
		em.merge(ordenDeCompra);
		}catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

}
