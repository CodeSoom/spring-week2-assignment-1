package com.codesoom.assignment.repository;


import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.storage.ListStorageRepositoryAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends ListStorageRepositoryAbstract<Task> {

}
