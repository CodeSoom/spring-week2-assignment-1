package com.codesoom.assignment.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListStorageRepository<T extends ListStorageEntity> implements ListStorage<T> {

    protected final List<T> list = new ArrayList<>();
    private Long listId = 0L;


    @Override
    public Optional<T> findById(Long id) {
        return list.stream().filter(it -> it.getId() == id).findFirst();
    }

    @Override
    public T save(T entity) {
        var target = findById(entity.getId());

        if (target.isEmpty()) {
            entity.setId(generateListId());
        }

        list.add(entity);

        return entity;
    }

    @Override
    public void deleteById(Long id) {
        var target = findById(id);

        if (target.isPresent()) {
            list.remove(target.get());
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

    private synchronized Long generateListId() {
        listId += 1;
        return listId;
    }

    public Long lastId() {
        return listId;
    }
}
