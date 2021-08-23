package com.codesoom.assignment.models;

public class Task {

    private Long id;
    private String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * id가 일치하면 true를 리턴하고, 아닐 경우 false를 리턴합니다.
     * @param id 비교 대상
     * @return id가 일치하는 경우 true, 그 외의 경우 false
     */
    public boolean isMatchId(long id) {
        return this.id.equals(id);
    }

    @Override
    public String toString() {
        return String.format("Task {id=%s, title=%s} ", id, title);
    }
}
