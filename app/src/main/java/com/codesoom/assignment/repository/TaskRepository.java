package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Task Entity의 데이터를 보관하고 이 Entity의 CRUD와 관련된 로직을 담당합니다.
 */
@SpringBootApplication(scanBasePackages={"com.codesoom.assignment.repository"})
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    /**
     * 저장된 할 일들(tasks)을 모두 반환합니다.
     *
     * @return 모든 할 일들(tasks)
     */
    public List<Task> findAll(){
        return tasks;
    }

    /**
     * 새로운 할 일(task)을 생성합니다.
     *
     * @param : 할 일(task)의 내용
     * @return : 저장된 할 일(task)을 반환합니다.
     */
    public Task create(Task task){
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    /**
     * 식별자(id)에 해당하는 할 일(task)을 수정한다.
     *
     * @param : 수정하려는 할 일(task)의 식별자(id)
     * @param : task(할 일)의 내용
     * @return 수정한 할 일(task)의 내용울 반환합니다.
     */
    public Task update(Long id, Task source){
        Task task = findTaskById(id);
        task.setTitle(source.getTitle());
        return task;
    }

    /**
     * 식별자(id)에 해당하는 할 일(task)을 삭제합니다.
     *
     * @param : 삭제하려는 할 일의 식별자
     * @return : 삭제 유무를 boolean 타입으로 반환합니다.
     */
    public boolean delete(Long id){
        Task task = findTaskById(id);
        boolean result = tasks.remove(task);
        return result;
    }

    /**
     * Task(할 일)의 식별자를 생성하고 리턴합니다.
     *
     * @return : 생성한 Task의 식별자
     */
    private Long generateId(){
        newId += 1;
        return newId;
    }

    /**
     * 전달받은 식별자에 해당하는 task(할 일)를 찾아 리턴합니다.
     *
     * @param : task(할 일)의 id(식별자)
     * @return 찾아낸 task(할 일)
     * @Exception ResponseStatusException(HttpStatus.NOT_FOUND)
     */
    public Task findTaskById(Long id){
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}