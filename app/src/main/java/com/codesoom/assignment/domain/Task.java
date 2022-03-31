package com.codesoom.assignment.domain;

import com.codesoom.assignment.common.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *  할일 도메인을 개념적으로 표현한 엔티티입니다.
 */
public class Task {
    private String taskId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    /**
     * 할일 도메인의 상태를 그룹화한 enum입니다.
     */
    private enum Status {
        REMOVE, INCOMPLETE, COMPLETION
    }

    /**
     * 전달 받은 아이디와 제목을 초기화 시킵니다.
     *
     * @param taskBuilder
     */
    private Task(TaskBuilder taskBuilder) {
        this.title = taskBuilder.title;
        this.taskId = taskBuilder.taskId;
    }

    /**
     * 할일 등록시 상태, 등록일자, 수정일자, 아이디를 초기화 시킵니다.
     * 객체의 상태를 스스로 책임시키기 위해 존재합니다
     *
     * @return Task
     */
    public Task registerTask() {
        this.status = Status.INCOMPLETE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.taskId = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, 8);
        return this;
    }

    public Task modifyTask(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public Task complete() {
        this.status = Status.COMPLETION;
        return this;
    }

    public Task remove() {
        this.status = Status.REMOVE;
        return this;
    }

    public static class TaskBuilder implements Builder<Task> {
        private String title;
        private String taskId;

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        @Override
        public Task build() {
            return new Task(this);
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Status getStatus() {
        return status;
    }
}
