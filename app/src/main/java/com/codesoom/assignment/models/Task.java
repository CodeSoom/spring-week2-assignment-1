package com.codesoom.assignment.models;

public class Task {
    private Long id;
    private String title;

    public Task(TaskBuilder taskBuilder) {
        this.id = taskBuilder.id;
        this.title = taskBuilder.title;
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

    public String toString() {
        return "Task - title: " + title;
    }

    public static class TaskBuilder {
        private Long id;
        private String title;

        public TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
