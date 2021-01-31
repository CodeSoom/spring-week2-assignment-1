package com.codesoom.assignment.application;

/**
 * 할 일을 찾을 수 없는 예외가 발생할 때 예외를 처리합니다.
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * 인자가 {@code null} 인 할 일을 찾을 수 없을 때의 생성자.
     */
    public TaskNotFoundException() {
        super("할 일을 찾을 수 없습니다.");
    }

    /**
     * 예외 메시지를 인자로 받는 할 일을 찾을 수 없을 때의 생성자.
     *
     * @param message 예외 사유를 담은 메시지
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * 예외처리 클래스를 인자로 받는 할 일을 찾을 수 없을 수 없을 때의 생성자.
     *
     * @param cause 발생한 모든 예외처리 클래스
     */
    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }
}
