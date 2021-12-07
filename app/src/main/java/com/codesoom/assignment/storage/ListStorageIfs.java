package com.codesoom.assignment.storage;

import java.util.List;
import java.util.Optional;

public interface ListStorageIfs<T> {

    Optional<T> findById(long id);
    T save(T entity);
    void deleteById(long id);
    void delete(T entity);
    List<T> findAll();
}
