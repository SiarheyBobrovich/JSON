package org.it_academy.MK_JD2_90_22.json.services.api;

import java.util.List;

public interface ICRUDService<T> {

    /**
     * Delegating to create a T-object in dao
     * @param t - The T-object which will be created
     */
    void create(T t);

    /**
     * Delegating to read a T-object in dao
     * @return - List of all the objects from db
     */
    List<T> select(String id);

    /**
     * Delegating to delete a T-object in dao
     * @param t - T-object which will be deleted
     */
    void delete(T t);


    /**
     * Delegating to update a T-object in dao
     * @param t - The T-object which will be updating
     */
    void update(T t);

}
