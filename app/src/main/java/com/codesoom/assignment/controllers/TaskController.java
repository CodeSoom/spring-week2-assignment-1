//TODO
// 1. Read Collection - GET /tasks => 완로
// 2. Read Item - GET /tasks/{id} => 완료
// 3. Create - POST / tasks => 완료
// 4. Update - PUT/PATH /tasks/{id} => 미완
// 5. Delete - DELETE /tasks/{id} => 완료

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public  List<Task> list(){
        return tasks;
    }

    //key대신 변수명을 무엇을 주면 좋을까요?
    @GetMapping("/{id}")
    public  ResponseEntity<Task> Detail(@PathVariable Long id){
        Task task = tasks.stream()
                .filter(key ->key.getId().equals(id))
                .findFirst()
                .orElse(null);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task){
        task.setId(generateId());
        tasks.add(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    @PutMapping("/{id}")
//    public ResponseEntity update(@PathVariable Long id, @RequestBody Task task){
//        Task task1 = tasks.stream()
//                .filter(key -> key.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//        task.setTitle(task1.getTitle());
//        return new ResponseEntity<>(task, HttpStatus.OK);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity Delete(@PathVariable Long id){
        Task task = tasks.stream()
                .filter(key -> key.getId().equals(id))
                .findFirst()
                .orElse(null);
        tasks.remove(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private  Long generateId(){
        newId += 1;
        return  newId;
    }
}
