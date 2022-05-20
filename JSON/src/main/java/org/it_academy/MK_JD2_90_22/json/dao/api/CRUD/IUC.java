package org.it_academy.MK_JD2_90_22.json.dao.api.CRUD;

public interface IUC<T> {

    /**
     * Updating params of a T-object
     * @param t - The T-object which will be updating
     */
    void update(T t);
}
