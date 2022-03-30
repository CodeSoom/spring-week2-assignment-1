package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.common.response.messages.error.ErrorCode;
import org.springframework.util.Assert;

/**
 * 클라이언트의 각 요청(등록,수정,삭제,완료)에 대응하는 정적멤버클래스가 존재하는 클래스입니다.
 *
 * @author Lee Byoung Seong
 * @version 1.0
 */
public class TodoDto {
    /**
     * Task등록 요청에 대응하는 정적멤버클래스입니다.
     */
    public static class RegisterTodoRequest {
        private final String title;

        /**
         * 제목을 초기화 시킵니다
         *
         * @param title 제목
         * @throws IllegalArgumentException {@code 제목}이 존재하지 않으면 던집니다.
         */
        public RegisterTodoRequest(String title) {
            Assert.hasText(title, ErrorCode.TITLE_IS_EMPTY.getErrorMsg());
            this.title = title;
        }

        /**
         * 제목을 건내 줍니다.
         *
         * @return 제목
         */
        public String getTitle() {
            return title;
        }
    }

    /**
     * 제목 수정 요청에 대응하는 정적멤버클래스 입니다.
     */
    public static class ModifyTodoTitleRequest {
        private final String taskId;
        private final String title;

        /**
         * 제목과 taskId를 초기화 시킵니다.
         *
         * @param taskId 아이디
         * @param title  제목
         * @throws IllegalArgumentException {@code 아이디}가 존재하지 않으면 던집니다.
         * @throws IllegalArgumentException {@code 제목}이 존재하지 않으면 던집니다.
         */
        public ModifyTodoTitleRequest(String taskId, String title) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            Assert.hasText(title, ErrorCode.TITLE_IS_EMPTY.getErrorMsg());

            this.taskId = taskId;
            this.title = title;
        }

        /**
         * 제목을 건내 줍니다
         *
         * @return 제목
         */
        public String getTitle() {
            return title;
        }

        /**
         * 아이디를 건내 줍니다
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

    /**
     * task삭제 요청에 대응하는 정적멤버클래스입니다.
     */
    public static class DeleteTodoRequest {
        private final String taskId;

        /**
         * 아이디를 초기화 시킵니다.
         *
         * @param taskId 아이디
         * @throws IllegalArgumentException {@code 아이디}가 존재하지 않으면 던집니다.
         */
        public DeleteTodoRequest(String taskId) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());

            this.taskId = taskId;
        }

        /**
         * 아이디를 건내 줍니다
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

    /**
     * task완료 요청에 대응하는 정적멤버클래스입니다.
     */
    public static class CompleteTodoRequest {
        private final String taskId;

        /**
         * 아이디를 초기화 시킵니다.
         *
         * @param taskId 아이디
         * @throws IllegalArgumentException {@code 아이디}가 존재하지 않으면 던집니다.
         */
        public CompleteTodoRequest(String taskId) {
            Assert.hasText(taskId, ErrorCode.TASK_ID_EMPTY.getErrorMsg());
            this.taskId = taskId;
        }

        /**
         * 아이디를 건내 줍니다
         *
         * @return 아이디
         */
        public String getTaskId() {
            return taskId;
        }
    }

}
