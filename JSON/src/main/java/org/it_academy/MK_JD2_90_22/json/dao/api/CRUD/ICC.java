package org.it_academy.MK_JD2_90_22.json.dao.api.CRUD;

public interface ICC<T> {

    /**
     * Create a T-object in the db
     * @param t - The T-object which will be created
     */
    void save(T t);
}
