package org.it_academy.MK_JD2_90_22.json.dao.api;

public interface ICrossController<T> {


    /**
     * Delegating to delete a T-object in dao
     * @param t - T-object which will be deleted
     */
    void delete(T t);


    /**
     * Delegating to update a T-object in dao
     * @param t - T-object which will be updating
     */
    void save(T t);

}
