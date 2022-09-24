package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 등록된 모든 할 일들을 리턴한다.
     * @return 등록된 모든 할 일
     */
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /**
     * 요청받은 할 일 ID로 해당 할 일을 조회해서 리턴한다.
     * @param id 요청 할 일 ID
     * @exception TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     * @return 검색된 할 일
     */
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(throwTaskException());
    }

    /**
     * 클라이언트가 입력한 할 일을 저장하고 저장한 할 일을 리턴한다.
     * @param task 입력한 할 일
     * @return 저장된 할 일
     */
    public Task insertTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * 수정할 할 일 ID와 수정내용을 받아서 해당 ID의 할 일을 수정내용으로 수정 후
     * 수정된 할 일을 리턴한다.
     * @param newTask 수정 할 일
     * @exception TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     * @return 수정된 할 일
     */
    public Task updateTask(Task newTask) {
        return taskRepository.update(newTask).orElseThrow(throwTaskException());

    }

    /**
     * 삭제할 할 일 ID를 받아서 해당 할 일을 삭제한다.
     * @param id 삭제할 할 일 ID
     */
    public void deleteTask(Long id) {
        taskRepository.delete(id).orElseThrow(throwTaskException());
    }

    /**
     * TaskNotFoundException을 발생시킨다.
     */
    private Supplier<TaskIdNotFoundException> throwTaskException() {
        return () -> new TaskIdNotFoundException("존재하지않는 아이디입니다.");
    }
}
