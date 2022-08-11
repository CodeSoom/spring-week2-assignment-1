package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface CRUDInterface<T> {

    List<T> selectAll();
    T selectById(Long id);
    T insert(TaskDTO taskDTO);
    T update(Long id , TaskDTO taskDTO);
    T delete(Long id);
}
