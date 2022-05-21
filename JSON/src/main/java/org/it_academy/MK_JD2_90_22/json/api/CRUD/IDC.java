package org.it_academy.MK_JD2_90_22.json.api.CRUD;

public interface IDC<D> {

    /**
     * Delete a D-object
     * @param d - D-object which will be deleted
     */
    void delete(D d);
}
