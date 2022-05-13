package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;

public interface ManipulatingRepository {

    void save(Task task);

    void update(Task task);

    void deleteBy(Long id);


    Task taskSaved();

    Task taskUpdated();
}
