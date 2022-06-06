package org.it_academy.courses.hibernate.api.CRUD;

public interface ICRUDController<C, R, U, D> extends ICreateController<C>, IReadController<R>, IUpdateCcontroller<U>, IDeleteController<D> {

}
