package com.codesoom.assignment.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Task {

    private final Long id;
    private final String title;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task() {
        this.id = 0L;
        this.title = "";
    }

    public Task updateTitle(String title) {
        return new Task(this.id, title);
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
