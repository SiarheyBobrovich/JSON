package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api;

import java.util.Map;
import java.util.Set;

public interface ICrossDaoController<G, S> {

    void save(G g, S s);

    void deleteG(G g);

    void deleteS(S s);

    Map<G, Set<S>> getAll();

    Set<S> getG(long id);

    G getS(long id);
}
