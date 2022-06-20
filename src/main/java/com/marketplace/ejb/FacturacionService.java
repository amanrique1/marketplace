package com.marketplace.ejb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.DAO.FacturacionDAO;
import com.marketplace.DAO.InventarioDAO;
import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.exceptions.DAOException;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.model.EstadoPedido;
import com.marketplace.model.Ubicacion;
import com.marketplace.model.BodyParams.CambioEstadoParams;
import com.marketplace.model.BodyParams.CrearOrdenDeCompraParams;
import com.marketplace.model.BodyParams.ProductoOrdenParams;
import com.marketplace.model.ResponseBody.IdObject;
import com.marketplace.model.ResponseBody.ListaOrdenesDeCompraResumen;
import com.marketplace.model.ResponseBody.OrdenDeCompraDetalle;
import com.marketplace.model.entity.CambioEstadoOrdenDeCompraEntity;
import com.marketplace.model.entity.OrdenDeCompraEntity;
import com.marketplace.model.entity.PersonaEntity;
import com.marketplace.model.entity.ProductoEntity;
import com.marketplace.model.entity.ProductoOrdenEntity;
import com.marketplace.model.entity.UbicacionEntity;
import com.marketplace.model.entity.UsuarioEntity;

@RequestScoped
public class FacturacionService {

	/**
	 * Objeto de conexion a la persistencia relacionada con la facturacion
	 */
	@Inject
	FacturacionDAO facturacion;

	/**
	 * Objeto de conexion a la persistencia relacionada con el inventario
	 */
	@Inject
	InventarioDAO inventario;

	/**
	 * Objeto de conexion a la persistencia relacionada con los usuarios
	 */
	@Inject
	UsuarioDAO usuario;

	/**
	 * Crea una orden de compra
	 * @param ordenDeCompraParams parametros necesarios para crear una orden de compra y producto orden
	 * @param emailPersona email del comprador
	 * @return id de la orden de compra
	 * @throws ServiceException si hay problemas guardando el orden producto o la orden de compra
	 */
	public IdObject crearOrdenDeCompra(CrearOrdenDeCompraParams ordenDeCompraParams, String emailPersona) throws ServiceException {
		try {
			OrdenDeCompraEntity ordenDeCompraEntity = new OrdenDeCompraEntity();
			UbicacionEntity ubicacion = new UbicacionEntity();
			Ubicacion ubicacionParam = ordenDeCompraParams.getUbicacion();
			ubicacion.setCiudad(ubicacionParam.getCiudad());
			ubicacion.setDetalles(ubicacionParam.getDetalles());
			ubicacion.setDireccion(ubicacionParam.getDireccion());
			ubicacion.setEstado(ubicacionParam.getEstado());
			ubicacion.setPais(ubicacionParam.getPais());
			ordenDeCompraEntity.setUbicacion(ubicacion);
			LocalDateTime fecha = LocalDateTime.now();
			PersonaEntity persona = usuario.buscarPersona(emailPersona);
			if(persona == null) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.persona.inexistente", String.class));
			}
			ordenDeCompraEntity.setEstado(EstadoPedido.INGRESADO);
			ordenDeCompraEntity.setFechaUltimaModificacion(fecha);
			ordenDeCompraEntity.setFecha(fecha);
			ordenDeCompraEntity.setNecesitaRevision(ordenDeCompraParams.getNecesitaRevision());
			ordenDeCompraEntity.setPersona(persona);

			List<ProductoOrdenEntity> productosOrden = new ArrayList<ProductoOrdenEntity>();
			for(ProductoOrdenParams prod : ordenDeCompraParams.getOrdenProductos()) {
				ProductoOrdenEntity prodEntity = new ProductoOrdenEntity();
				ProductoEntity producto = inventario.darProductoEntity(prod.getIdProducto());
				if(producto == null) {
					throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.producto.inexistente", String.class),Response.Status.BAD_REQUEST);
				} else if(!producto.getAprobado()) {
					throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.producto.no.aprobado", String.class),Response.Status.BAD_REQUEST);
				}
				prodEntity.setProducto(producto);
				prodEntity.setCantidadProducto(prod.getCantidad());
				prodEntity.setDetalles(prod.getDetalles().get());
				prodEntity.setValorTotal(prod.getCantidad()*producto.getPrecioTotal());
				prodEntity.setUnidadMonetaria(producto.getMoneda());
				prodEntity.setOrdendecompra(ordenDeCompraEntity);
				productosOrden.add(prodEntity);
			}
			ordenDeCompraEntity.setProductoOrden(productosOrden);
			List<CambioEstadoOrdenDeCompraEntity> cambios = new ArrayList<CambioEstadoOrdenDeCompraEntity>();
			CambioEstadoOrdenDeCompraEntity cambEstado = new CambioEstadoOrdenDeCompraEntity();
			cambEstado.setEstado(EstadoPedido.INGRESADO.toString());
			cambEstado.setFechaModificacion(fecha);
			cambEstado.setUsuarioModificador(persona);
			cambEstado.setOrdenDeCompra(ordenDeCompraEntity);
			cambios.add(cambEstado);
			ordenDeCompraEntity.setCambioEstado(cambios);
			ubicacion.setOrdenDeCompra(ordenDeCompraEntity);
			//Persisto la ubicacion por que es la duenia de la relacion con orden de compra
			facturacion.guardarUbicacion(ubicacion);
			return new IdObject(ordenDeCompraEntity.getId());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Busca una orden de compra a partir de su id
	 * @param id de la orden de compra
	 * @return OrdenDeCompraDetalle
	 * @throws ServiceException si no existe una orden de compra para ese id
	 */
	public OrdenDeCompraDetalle darOrdenDeCompra(Long id) throws ServiceException {
		try {
			return facturacion.darOrdenDeCompra(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca una orden de compra a partir del email de una persona
	 * @param email de la persona
	 * @param posInicial de busqueda (paginacion)
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws ServiceException
	 */
	public ListaOrdenesDeCompraResumen darOrdenesDeCompraPersona(String email,Integer posInicial,Integer limite) throws ServiceException {
		try {
			ListaOrdenesDeCompraResumen ordenes = facturacion.darOrdenDeCompraPersona(email,posInicial,limite);
			if(!usuario.existePersona(email)) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.persona.inexistente", String.class));
			}
			return ordenes;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca una orden de compra a partir de su estado y el email de una persona
	 * @param email de la persona
	 * @param estado de las ordenes de compra
	 * @param posInicial de busqueda (paginacion)
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws ServiceException
	 */
	public ListaOrdenesDeCompraResumen darOrdenesDeCompraPersonaEstado(String email,String estado,Integer posInicial,Integer limite) throws ServiceException {
		EstadoPedido estadoEnum = null;
		try {
			estadoEnum = EstadoPedido.valueOf(estado);
		}catch (Exception e) {
			throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.estado.inexistente", String.class),e);
		}
		if(!usuario.existePersona(email)) {
			throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.persona.inexistente", String.class));
		}
		try {
			return facturacion.darOrdenDeCompraPersonaEstado(email,estadoEnum,posInicial,limite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Busca una orden de compra a partir de su estado
	 * @param estado de las ordenes de compra
	 * @param posInicial de busqueda (paginacion)
	 * @param limite de resultados a retornar
	 * @return ListaOrdenesDeCompraResumen
	 * @throws ServiceException
	 */
	public ListaOrdenesDeCompraResumen darOrdenesDeCompraEstado(String estado,Integer posInicial,Integer limite) throws ServiceException {
		EstadoPedido estadoEnum = null;
		try {
			estadoEnum = EstadoPedido.valueOf(estado);
		}catch (Exception e) {
			throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.estado.inexistente", String.class),e);
		}
		try {
			return facturacion.darOrdenDeCompraEstado(estadoEnum,posInicial,limite);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Cambia el estado de una orden de compra y actualiza su historial de estados
	 * @param nuevoEstado
	 * @param idOrden
	 * @param emailModificador
	 * @throws ServiceException si no existe el estado especificado o hay problemas actualizando el objeto
	 */
	public void cambiarEstadoOrdenCompra(CambioEstadoParams nuevoEstado, Long idOrden, String emailModificador) throws ServiceException{
		try {
			OrdenDeCompraEntity orden = facturacion.darOrdenDeCompraEntity(idOrden);
			LocalDateTime fecha = LocalDateTime.now();
			EstadoPedido estadoEnum = null;
			try {
				estadoEnum = EstadoPedido.valueOf(nuevoEstado.getNuevoEstado());
			}catch (Exception e) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.estado.inexistente", String.class),e);
			}
			UsuarioEntity persona = usuario.buscarUsuario(emailModificador);
			CambioEstadoOrdenDeCompraEntity cambEstado = new CambioEstadoOrdenDeCompraEntity();
			cambEstado.setEstado(estadoEnum.toString());
			cambEstado.setFechaModificacion(fecha);
			cambEstado.setUsuarioModificador(persona);
			cambEstado.setOrdenDeCompra(orden);
			orden.agregarCambioEstado(cambEstado);
			orden.setFechaUltimaModificacion(fecha);
			orden.setEstado(estadoEnum);
			facturacion.actualizarOrdenDeCompra(orden);
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}
}
