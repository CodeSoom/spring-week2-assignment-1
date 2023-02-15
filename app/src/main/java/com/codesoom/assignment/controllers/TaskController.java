package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.NoDataException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long rowId = 0L;
    @GetMapping("")
    public List<Task> list(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable("id") Long id){
        return findTask(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task){

        return  generateTask(task);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task update(@PathVariable("id") Long id, @RequestBody Task task){

        return  updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){

        deleteTask(id);
    }

    /**
     * List.stream() : Stream<Object>를 리턴한다. Stream이란 컬렉션에 저장되어 있는 엘리먼트들을 하나씩 순회하면서 처리할 수 있는 코드패턴.
     * stream().filter(method) : filter는 인자로 함수를 받으며 특정 조건으로 요소들을 필터링한다.
     * task -> task.getId().equals(id) : 스트림에서 task를 하나하나 빼내면서 task.getId()와 요청 id값이 일치하는 요소를 조회
     * findFirst() : Stream 에서 가장 먼저 탐색되는 요소를 Optional로 리턴\
     * Optional : null이 올 수 있는 값을 감싸는 Wrapper 클래스. 멤버필드가 null이라도 NPE가 발생하지 않도록 도와준다.
     * orElse(null) : Optional 클래스의 value 값이 null이 아닐 경우 해당 value(객체)를 리턴하고, null일 경우 null을 리턴한다.
     * orElse(exception) : Optional 클래스의 값이 null이 아닐 경우 해당 value(객체)를 리턴하고, null일 경우 exception을 던진다.
     *
     *
     * NoDataException::new :
     *  1) Supplier 인터페이스는 FunctionalInterface이므로 익명클래스를 구현하여 사용한다.
     *   new Supplier<NoDataException>() {
     *            @Override
     *            public NoDataException get() {
     *                return new NoDataException();
     *            }
     *       }
     *
     *  2) 이를 람다식으로 표현하면 () ->new NoDataException() 로 나타낼 수 있다.
     *  3) 이를 더블콜론으로 표현하면 NoDataException::new로 나타낼 수 있다.
     */
    private Task findTask(Long id){

        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(NoDataException::new);
    }

    private Task generateTask(Task task){
        increaseTaskId();
        task.setId(rowId);
        tasks.add(task);

        return task;
    }

    private Task updateTask(Long id, Task source){
        Task task = findTask(id);
        task.setTitle(source.getTitle());

        return task;
    }

    private void deleteTask(Long id){
        Task task = findTask(id);
        tasks.remove(task);
    }

    synchronized private void increaseTaskId(){
        rowId += 1;
    }
}
