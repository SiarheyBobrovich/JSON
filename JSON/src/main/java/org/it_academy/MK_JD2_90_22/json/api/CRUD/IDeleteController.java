package org.it_academy.MK_JD2_90_22.json.api.CRUD;

public interface IDeleteController<D> {

    /**
     * Delete a D-object
     * @param d - D-object which will be deleted
     */
    void delete(D d);
}
