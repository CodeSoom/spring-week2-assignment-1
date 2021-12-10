package com.codesoom.assignment.storage;

import java.util.List;
import java.util.Optional;

public interface ListStorage<T> {

    Optional<T> findById(Long id);
    T save(T entity);
    void deleteById(Long id);
    void delete(T entity);
    List<T> findAll();
}
