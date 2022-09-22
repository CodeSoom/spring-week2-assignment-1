package com.codesoom.assignment.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Task {

    private Long id;
    private String title;

    public Task() {
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private Long id;
        private String title;

        TaskBuilder() {
        }

        public TaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.title);
        }
    }

}
