package com.marketplace.DAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.marketplace.exceptions.DAOException;
import com.marketplace.model.entity.PQRSEntity;

@RequestScoped
public class PQRSDAO {
	
	/**
	 * Manejador de la persistencia
	 */
	@Inject
	EntityManager em;
	
	/**
	 * Crea la pqs en la BD
	 * @param pqs a persistir
	 * @throws DAOException si hay problemas persistiendo el objeto
	 */
	@Transactional
	public void crearPQS(PQRSEntity pqs) throws DAOException{
		try {
			em.persist(pqs);
		}catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
}
