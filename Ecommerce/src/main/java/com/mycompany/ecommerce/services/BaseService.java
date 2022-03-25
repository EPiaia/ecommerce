package com.mycompany.ecommerce.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piaia
 */
public abstract class BaseService {

    @PersistenceContext(name = "Ecommerce")
    protected EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

}
