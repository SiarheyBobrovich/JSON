package org.it_academy.courses.hibernate.api.CRUD;

import java.util.List;

public interface IReadController<R> {

    /**
     * Read all R-objects
     * @return - List of all the R-objects
     */
    List<R> getAll();

    /**
     * Read a R-object
     * @param id - R-object
     * @return - R-object has been read
     */
    R get(long id);

}
