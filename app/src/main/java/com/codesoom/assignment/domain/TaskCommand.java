package com.codesoom.assignment.domain;

import com.codesoom.assignment.common.Builder;
import com.codesoom.assignment.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

/*
    외부에서 직접적으로 엔티티 개념인 Task를 접근하지 않기 위해 작성한 인터페이스 객체입니다
 */
public class TaskCommand {
    public static class RegisterTodoRequest implements Builder<Task> {
        private String title;

        public RegisterTodoRequest title(String title) {
            this.title = title;
            return this;
        }

        @Override
        public Task build() {
            Task task = new Task.TaskBuilder()
                    .title(this.title)
                    .build();
            task.registerTask();
            return task;
        }
    }

    public static class ModifyTodoTitleRequest implements Builder<Task> {
        private String taskId;
        private String title;

        public ModifyTodoTitleRequest title(String title) {
            this.title = title;
            return this;
        }

        public ModifyTodoTitleRequest taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        @Override
        public Task build() {
            Task task = new Task.TaskBuilder()
                    .title(this.title)
                    .taskId(this.taskId)
                    .build();
            return task;
        }
    }

    public static class RemoveTodoRequest {
        private final String taskId;

        RemoveTodoRequest(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskId() {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            return taskId;
        }
    }

    public static class CompleteTodoRequest {
        private final String taskId;

        CompleteTodoRequest(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskId() {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            return taskId;
        }
    }


}
