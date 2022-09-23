package com.codesoom.assignment.domain;

import org.springframework.util.Assert;

/** 할 일 */
public class Task {
    private final Long id;
    private final String title;

    private Task(Long id, String title) {
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
     *
     * @param title 업데이트할 title
     * @throws IllegalArgumentException title이 없을 경우
     * @return 변경된 title을 갖는 새로운 할 일
     */
    public Task withNewTitle(String title) {
        Assert.notNull(title, "title은 빈 값일 수 없습니다");
        return new Task(this.id, title);
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
            Assert.notNull(this.id, "id는 빈 값일 수 없습니다");
            Assert.notNull(this.title, "title은 빈 값일 수 없습니다");

            return new Task(this.id, this.title);
        }
    }
}
