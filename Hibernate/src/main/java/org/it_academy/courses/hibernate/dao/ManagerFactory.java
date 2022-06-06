package org.it_academy.courses.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManagerFactory implements AutoCloseable{

    private static final ManagerFactory instance;

    private final EntityManagerFactory factory;

    static {
        instance = new ManagerFactory();
    }

    private ManagerFactory() {
        factory = Persistence.createEntityManagerFactory
                ("org.it_academy.courses.hibernate.dao.entity");
    }

    public EntityManager getManager() {
        return factory.createEntityManager();
    }

    public static ManagerFactory getInstance() {
        return instance;
    }

    @Override
    public void close() throws Exception {
        factory.close();
    }
}
