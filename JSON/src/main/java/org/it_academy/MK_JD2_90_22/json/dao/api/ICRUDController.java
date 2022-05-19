package org.it_academy.MK_JD2_90_22.json.dao.api;

import java.util.List;

public interface ICRUDController<T> {

    /**
     * Create a T-object in the db
     * @param t - The T-object which will be created
     */
    void save(T t);

    /**
     * Read all T-objects from the db
     * @return - List of all the objects from db
     */
    List<T> get(String id);

    /**
     * Updating params of a T-object
     * @param t - The T-object which will be updating
     */
    void update(T t);

    /**
     * Delete a T-object from the db
     * @param t - T-object which will be deleted
     */
    void delete(T t);
}
