package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.dao.api.CRUD.ICC;
import org.it_academy.MK_JD2_90_22.json.dao.api.CRUD.IDC;
import org.it_academy.MK_JD2_90_22.json.dao.api.CRUD.IUC;

public interface ICUDController<T> extends ICC<T>, IUC<T>, IDC<T> {
}
