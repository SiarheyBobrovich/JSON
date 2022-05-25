package org.it_academy.MK_JD2_90_22.json.api.CRUD;

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
