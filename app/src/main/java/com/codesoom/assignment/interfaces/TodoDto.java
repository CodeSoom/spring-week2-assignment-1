package com.codesoom.assignment.interfaces;

import org.springframework.util.Assert;

/*
    Todo를 등록,삭제,수정등 클라이언트로 부터 전달 받은 데이터를 각 액션마다  Paramter Object를 만들었습니다.
    인터넷 매체에 많은 정보가 존재하지 않아 리팩토링에서 제시한  Paramter Object를 제대로 구현했는지 확신이 서지 않습니다..ㅠㅠ
 */
public class TodoDto {

    public static class RegisterTodoRequest {
        private final String title;

        public RegisterTodoRequest(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class ModifyTodoTitleRequest {
        private final String taskId;
        private final String title;

        public ModifyTodoTitleRequest(String taskId, String title) {
            this.taskId = taskId;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public String getTaskId() {
            return taskId;
        }
    }

    public static class DeleteTodoRequest {
        private final String taskId;

        public DeleteTodoRequest(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskId() {
            return taskId;
        }
    }
}
