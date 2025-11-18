package com.hawkshaw.library.datalayer.room;

import a3.e;

public interface BaseDao<T> {
    Object delete(T t4, e eVar);

    Object deleteAll(T[] tArr, e eVar);

    Object insert(T[] tArr, e eVar);

    void insertSync(T... tArr);

    Object update(T[] tArr, e eVar);

    void updateSync(T... tArr);
}
