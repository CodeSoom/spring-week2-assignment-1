package com.codesoom.assignment.models;

public class Task {

    private static Long count = 0L;
    private Long id;
    private String title;

    public Task(){

    }

    public Task(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Task updateTitle(String title) {
        return new Task(id, title);
    }

    public String toString(){
        return "Task - title: " + title;
    }

}
