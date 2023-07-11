package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.common.GenericResultClass;

public interface IService<T> {

    public GenericResultClass getAll();
    public GenericResultClass upsert(T model);

    public GenericResultClass delete(long id);

}
