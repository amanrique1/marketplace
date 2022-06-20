package com.marketplace.ejb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;

import com.marketplace.DAO.FacturacionDAO;
import com.marketplace.DAO.PQRSDAO;
import com.marketplace.DAO.UsuarioDAO;
import com.marketplace.exceptions.DAOException;
import com.marketplace.exceptions.ServiceException;
import com.marketplace.model.EstadosPQRS;
import com.marketplace.model.TipoPQRS;
import com.marketplace.model.BodyParams.CrearPQRSParams;
import com.marketplace.model.ResponseBody.IdObject;
import com.marketplace.model.entity.CambioEstadoPQRSEntity;
import com.marketplace.model.entity.PQRSEntity;
import com.marketplace.model.entity.ProductoOrdenEntity;
import com.marketplace.model.entity.ProveedorEntity;
import com.marketplace.model.entity.UsuarioEntity;

@RequestScoped
public class PQRSService {

	@Inject 
	PQRSDAO pqrsDAO;

	@Inject
	UsuarioDAO usuarioDAO;

	@Inject
	FacturacionDAO facturacionDAO;

	public IdObject crearPQS(CrearPQRSParams pqs,String email) throws ServiceException{
		PQRSEntity pqsEnt = new PQRSEntity();
		pqsEnt.setDescripcion(pqs.getDescripcion());
		TipoPQRS tipo = null;
		try {
			tipo = TipoPQRS.valueOf(pqs.getTipo());
			pqsEnt.setTipo(TipoPQRS.valueOf(pqs.getTipo()));
		} catch (Exception e) {
			throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.tipo.inexistente", String.class),e);
		}
		LocalDateTime fecha = LocalDateTime.now();
		UsuarioEntity usuario = usuarioDAO.buscarUsuario(email);
		pqsEnt.setUsuario(usuario);
		List<CambioEstadoPQRSEntity> cambios = new ArrayList<CambioEstadoPQRSEntity>();
		CambioEstadoPQRSEntity cambEstado = new CambioEstadoPQRSEntity();
		cambEstado.setEstado(EstadosPQRS.ENVIADA.toString());
		cambEstado.setFechaModificacion(fecha);
		cambEstado.setUsuarioModificador(usuario);
		cambEstado.setPqs(pqsEnt);
		cambios.add(cambEstado);
		pqsEnt.setCambioEstado(cambios);
		pqsEnt.setFechaUltimaModificacion(fecha);
		pqsEnt.setEstado(EstadosPQRS.ENVIADA);
		try {
			if(tipo == TipoPQRS.RECLAMO) {
				Boolean prov= ProveedorEntity.class.isAssignableFrom(usuario.getClass());
				if(prov) {
					throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.reclamo.producto.proveedor", String.class));
				}
				if(pqs.getIdOrdenProducto() != null) {
					ProductoOrdenEntity prod = facturacionDAO.darProductoOrden(pqs.getIdOrdenProducto().get());
					if(prod == null) {
						throw new ServiceException(ConfigProvider.getConfig().getValue("mensaje.productoOrden.inexistente", String.class));
					}
					pqsEnt.setProductoOrden(prod);
				}

			}
			pqrsDAO.crearPQS(pqsEnt);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		return new IdObject(pqsEnt.getId());
	}


}
