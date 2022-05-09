package com.codesoom.assignment.domain;

public class Task {

    private Long id = 0L;
    private String title;

    public Task() {}

    /**
     * Task 생성 시, id 자동으로 생성하기 위한 생성자
     * @param title
     */
    public Task(String title) {
        this.id = generateId();
        this.title = title;
    }

    /**
     * id 자동 생성
     * @return id
     */
    private Long generateId() {
        return this.id++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
