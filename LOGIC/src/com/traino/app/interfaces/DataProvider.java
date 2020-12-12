package com.traino.app.interfaces;

import java.util.List;

public interface DataProvider<T> {
    T get(long id);
    List<T> getAll(String[] params);
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
    T get(String[] params);
}

