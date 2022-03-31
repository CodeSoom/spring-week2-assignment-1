package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

/**
 * 클라이언트의 각 요청(등록,수정,삭제,완료)에 대응합니다.
 */
public class TodoDto {
    /**
     * 클라이언트로 부터 할일 등록 요청에 대응합니다
     */
    public static class RegisterTodoRequest {
        private final String title;

        /**
         * 클라이언트로 부터 전달받은 제목을 할당합니다
         *
         * @param title 제목
         * @throws IllegalArgumentException 클라이언트로부터 제목을 전달 받지 않은 경우
         */
        public RegisterTodoRequest(String title) {
            Assert.hasText(title, ErrorCode.TITLE_IS_EMPTY.getErrorMsg());
            this.title = title;
        }

        /**
         * 클라이언트로 부터 전달받은 제목을 반환합니다.
         *
         * @return 제목
         */
        public String getTitle() {
            return title;
        }
    }

    /**
     * 클라이언트로 부터 제목 수정 요청에 대응합니다
     */
    public static class ModifyTodoTitleRequest {
        private final String taskId;
        private final String title;

        /**
         * 클라이언트로 전달받은 제목과 할일의 아이디를 할당합니다
         *
         * @param taskId 아이디
         * @param title  제목
         * @throws IllegalArgumentException 클라이언트로부터 할일의 아이디을 전달 받지 않은 경우
         * @throws IllegalArgumentException 클라이언트로부터 제목을 전달 받지 않은 경우.
         */
        public ModifyTodoTitleRequest(String taskId, String title) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            Assert.hasText(title, ErrorCode.TITLE_IS_EMPTY.getErrorMsg());

            this.taskId = taskId;
            this.title = title;
        }

        /**
         * 클라이언트로 부터 전달받은 제목을 반환합니다.
         *
         * @return 제목
         */
        public String getTitle() {
            return title;
        }

        /**
         * 클라이언트로 부터 전달받은 아이디를 반환합니다.
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

    /**
     * 클라이언트로 부터 할일 삭제 요청에 대응합니다
     */
    public static class DeleteTodoRequest {
        private final String taskId;

        /**
         * 클라이언트로 전달받은 아이디를 초기화 시킵니다.
         *
         * @param taskId 아이디
         * @throws IllegalArgumentException 클라이언트로부터 할일의 아이디을 전달 받지 않은 경우
         */
        public DeleteTodoRequest(String taskId) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());

            this.taskId = taskId;
        }

        /**
         * 클라이언트로 부터 전달받은 아이디를 반환합니다.
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

    /**
     * 클라이언트로 부터 할일 완료 요청에 대응합니다
     */
    public static class CompleteTodoRequest {
        private final String taskId;

        /**
         * 클라이언트로 전달받은 아이디를 초기화 시킵니다.
         *
         * @param taskId 아이디
         * @throws IllegalArgumentException 클라이언트로부터 할일의 아이디을 전달 받지 않은 경우
         */
        public CompleteTodoRequest(String taskId) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            this.taskId = taskId;
        }

        /**
         *  클라이언트로 부터 전달받은 아이디를 반환합니다.
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

}
