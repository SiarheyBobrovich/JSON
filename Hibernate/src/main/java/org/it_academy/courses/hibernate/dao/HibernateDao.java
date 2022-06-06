package org.it_academy.courses.hibernate.dao;

import jakarta.persistence.EntityManager;
import org.it_academy.courses.hibernate.api.CRUD.ICRUDController;
import org.it_academy.courses.hibernate.dao.api.IEntity;

public abstract class HibernateDao<T extends IEntity> implements ICRUDController<T, T, T, T> {

    private final ManagerFactory factory;

    protected HibernateDao() {
        this.factory = ManagerFactory.getInstance();
    }

    public ManagerFactory getFactory() {
        return factory;
    }

    public long save(T t) {
        EntityManager manager = factory.getManager();

        manager.getTransaction().begin();

        manager.persist(t);

        manager.getTransaction().commit();
        manager.close();

        return t.getId();
    }

    @Override
    public void delete(T t) {
        EntityManager manager = factory.getManager();

        manager.getTransaction().begin();

        manager.remove(t);

        manager.getTransaction().commit();
        manager.close();
    }
}
