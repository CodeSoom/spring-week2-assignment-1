package com.codesoom.assignment.domain;

import com.codesoom.assignment.common.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private String taskId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    private enum Status{
        REMOVE,INCOMPLETE,COMPLETE
    }
    private Task(TaskBuilder taskBuilder) {
        this.title = taskBuilder.title;
        this.taskId = taskBuilder.taskId;
    }

    public Task registerTask() {
        this.status=Status.INCOMPLETE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.taskId = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(0, 8);
        return this;
    }

    /*
         task아이디를 통헤서 기존에 등록된 task를 가져온 후
         title과 update시간을 변경할 예정입니다.
     */
    public Task modifyTask(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public Task complete(){
        this.status=Status.COMPLETE;
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
