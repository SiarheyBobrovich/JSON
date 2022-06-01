package org.it_academy.MK_JD2_90_22.json2.dao.api;

import java.util.List;
import java.util.Map;

public interface ICrossDaoController<G, S> {

    void save(G g, S s);

    void deleteG(G g);

    void deleteS(S s);

    Map<G, List<S>> getAll();

    List<S> getG(long id);

    G getS(long id);
}
