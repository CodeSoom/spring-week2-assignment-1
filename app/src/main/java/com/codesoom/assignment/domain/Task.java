package com.codesoom.assignment.domain;

/** 할 일 */
public class Task {
    private Long id;
    private String title;

    Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /**
     * title을 업데이트 합니다.
     * @param title 업데이트 할 title
     */
    public void updateTitle(String title) {
        this.title = title;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private Long id;
        private String title;

        TaskBuilder() {}

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
