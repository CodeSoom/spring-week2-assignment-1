package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskService {

    /**
     * 모든 할 일을 리턴합니다.
     */
    List<Task> getTasks();

    /**
     * 주어진 할 일을 저장한 뒤, 저장된 할 일을 리턴합니다.
     *
     * @param task 저장 하고자 하는 할 일
     * @return 저장 된 할 일
     */
    Task addTask(Task task);

    /**
     * 주어진 id에 해당하는 할 일을 찾아 리턴하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 찾고자 하는 할 일의 id
     * @return 주어진 id에 해당하는 할 일
     * @throws java.util.NoSuchElementException id가 존재하지 않으므로 예외 발생
     */
    Task getTask(Long id);

    /**
     * 주어진 id에 해당하는 할 일을 찾아 수정하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 수정하고자 하는 할 일의 id
     * @return 수정된 할 일
     * @throws java.util.NoSuchElementException id가 존재하지 않으므로 예외 발생
     */
    Task updateTask(Long id, Task newTask);

    /**
     * 주어진 id에 해당하는 할 일을 찾아 삭제하거나 찾지 못한다면 TaskNotFoundException 예외가 발생합니다.
     *
     * @param id 삭제하고자 하는 할 일의 id
     * @throws  java.util.NoSuchElementException id가 존재하지 않으므로 예외 발생
     */
    void deleteTask(Long id);
}
