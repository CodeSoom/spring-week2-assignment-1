/*
# ToDo
- 목록 얻기 - `GET /tasks`
- 상세 조회하기 - `GET /tasks/{id}`
- 생성하기 - `POST /tasks`
- 제목 수정하기 - `PUT/PATCH /tasks/{id}`
- 삭제하기 - `DELETE /tasks/{id}`
 */

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    private Long generatedId(){
        newId += 1;
        return newId;
    }

    @GetMapping()
    public List<Task> getAll() {
        return tasks;
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task.setId(generatedId());
        tasks.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> read(@PathVariable("id") Long id) {
        Optional<Task> task = tasks.stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Task> update(@RequestBody Task updateTask, @PathVariable("id") Long id) {
        Optional<Task> task = tasks.stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            if(updateTask != null){
                task.get().setTitle(updateTask.getTitle());
            }
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> partialUpdate(@RequestBody Task updateTask, @PathVariable("id") Long id) {
        Optional<Task> task = tasks.stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            if(updateTask != null){
                task.get().setTitle(updateTask.getTitle());
            }
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Task> task = tasks.stream().filter(t -> t.getId().equals(id))
                .findFirst();
        if(task.isPresent()){
            tasks.remove(task.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
