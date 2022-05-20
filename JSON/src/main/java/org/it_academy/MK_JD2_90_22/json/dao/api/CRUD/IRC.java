package org.it_academy.MK_JD2_90_22.json.dao.api.CRUD;

import java.util.List;

public interface IRC<T> {

    /**
     * Read all T-objects from the db
     * @return - List of all the objects from db
     */
    List<T> getAll();

    T get(long id);

}
