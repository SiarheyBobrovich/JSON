package org.it_academy.MK_JD2_90_22.json.api.CRUD;

import java.util.List;

public interface IRC<R> {

    /**
     * Read all R-objects
     * @return - List of all the R-objects
     */
    List<R> getAll();

    R get(long id);

}
