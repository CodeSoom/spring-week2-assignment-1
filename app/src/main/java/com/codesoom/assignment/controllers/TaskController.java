package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    private HashMap<Long, Task> taskHashMap = new HashMap<Long, Task>();

    private Long newId = 0L;

    @GetMapping
    public List<Task> getTasks(){

        List<Task> taskList = new ArrayList<>(taskHashMap.values());

        return taskList;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        if(taskHashMap.containsKey(taskId)) {
            Task task = taskHashMap.get(taskId);
            return new ResponseEntity<>(task,headers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,headers, HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Long taskId = generateId();

        task.setId(taskId);

        taskHashMap.put(taskId, task);

        return new ResponseEntity<>(task, headers, HttpStatus.CREATED);

    }

    @PatchMapping("/{taskId}")
    @PutMapping("/{taskId}")
    public ResponseEntity modifyTask(@RequestBody Task task, @PathVariable Long taskId){


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        if(taskHashMap.containsKey(taskId)) {
            taskHashMap.get(taskId).setTitle(task.getTitle());
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long taskId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        if(taskHashMap.containsKey(taskId)) {
            taskHashMap.remove(taskId);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    private Long generateId() {
        return newId++;
    }
}
