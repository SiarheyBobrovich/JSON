package org.it_academy.MK_JD2_90_22.json.dao.api.CRUD;

public interface IDC<T> {

    /**
     * Delete a T-object from the db
     * @param t - T-object which will be deleted
     */
    void delete(T t);
}
