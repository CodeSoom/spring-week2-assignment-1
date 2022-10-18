package com.codesoom.assignment.models;

public class TaskDto extends Decorator {

    private Long id;
    private String title;

    public TaskDto() {
    }

    @Override
    public Long getId() {
        return !super.hasBaskTask() ? super.getId() : this.id;
    }

    public void setId(Long id) {
        this.id = id;
        if (this.title != null) {
            super.setBaseTask(new Task(id, title));
        }
    }

    @Override
    public String getTitle() {
        return !super.hasBaskTask() ? super.getTitle() : this.title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (this.id != null) {
            super.setBaseTask(new Task(id, title));
        }
    }

    public BaseTask toTask() {
        return super.getBaseTask();
    }
}
