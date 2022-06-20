package com.marketplace.ejb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.DAO.ProveedorDAO;
import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.exceptions.DAOException;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.model.ResponseBody.ListaCotizaciones;
import com.marketplace.model.ResponseBody.ListaOrdenesDeCompraProveedor;
import com.marketplace.model.ResponseBody.ListaProductos;

@RequestScoped
public class ProveedorService {

	/**
	 * Objeto encargado de la persistencia
	 */
	@Inject
	ProveedorDAO proveedorDAO;
	
	/**
	 * Objeto encargado de la persistencia
	 */
	@Inject
	UsuarioDAO usuarioDAO;
	
	/**
	 * Busca en la persistencia las cotizaciones de un proveedor
	 * @param emailProveedor a buscar
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de cotizaciones
	 */
	public ListaCotizaciones darCotizacionesProveedor(String emailProveedor,Integer posInicial, Integer limite) {
		return proveedorDAO.darCotizacionesProveedor(emailProveedor, posInicial,limite);
	}
	
	/**
	 * Busca en la persistencia los productos de un proveedor
	 * @param emailProveedor a buscar
	 * @param posInicial de busqueda
	 * @param limite de resultados a devolver
	 * @return lista de cotizaciones
	 * @throws ServiceException si no existe la categoria
	 */
	public ListaProductos darProductosProveedor(String emailProveedor,Integer posInicial, Integer limite) throws ServiceException {
		try {
			return proveedorDAO.darProductosPorProveedor(emailProveedor, posInicial,limite);
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
	public ListaOrdenesDeCompraProveedor darOrdenesDeCompraProveedor(String email,Integer posInicial,Integer limite) throws ServiceException {
		try {
			ListaOrdenesDeCompraProveedor ordenes = proveedorDAO.darOrdenDeCompraProveedor(email,posInicial,limite);
			if(!usuarioDAO.existeProveedor(email)) {
				throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.proveedor.inexistente", String.class));
			}
			return ordenes;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
