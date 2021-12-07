package com.codesoom.assignment.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListStorageRepositoryAbstract<T extends ListStorageEntity> implements ListStorageIfs<T> {

    protected final List<T> list = new ArrayList<>();
    private Long indexId = 0L;


    @Override
    public Optional<T> findById(Long id) {
        return list.stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public T save(T entity) {
        var optionalEntity = list.stream().filter(it -> it.getId() == entity.getId()).findFirst();

        if (optionalEntity.isEmpty()) {
            entity.setId(generateId());
        }

        list.add(entity);

        return entity;
    }

    @Override
    public void deleteById(Long id) {
        var optionalEntity = list.stream().filter(it -> it.getId() == id).findFirst();

        if (optionalEntity.isPresent()) {
            list.remove(optionalEntity.get());
        }
    }

    @Override
    public void delete(T entity) {
        list.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return list;
    }

    private Long generateId() {
        indexId += 1;
        return indexId;
    }

    public Long lastId() {
        return indexId;
    }
}
