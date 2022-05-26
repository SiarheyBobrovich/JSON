package org.it_academy.MK_JD2_90_22.json2.api.CRUD;

public interface ICreateController<C> {

    /**
     * Save a C-object
     * @param c - The C-object which will be saved
     */
    void save(C c);
}
