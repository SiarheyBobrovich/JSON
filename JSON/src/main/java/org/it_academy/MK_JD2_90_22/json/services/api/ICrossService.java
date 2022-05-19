package org.it_academy.MK_JD2_90_22.json.services.api;

import java.util.List;

public interface ICrossService<T> {


    /**
     * Delegating to delete a T-object in dao
     * @param t - List of T-object which will be deleted
     */
    void delete(List<T> t);


    /**
     * Delegating to update a T-object in dao
     * @param t - List of T-object which will be updating
     */
    void save(List<T> t);
}
