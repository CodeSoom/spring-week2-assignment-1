package com.codesoom.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 할 일 목록에서 해당하는 id를 찾을 수 없는 예외상황을 처리합니다.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Such Id doesn't exists in Tasks")
public class TaskIdNotFoundException extends RuntimeException {
    public String notFoundTaskId() {
        return "입력하신 id는 존재하지 않는 아이디입니다.";
    }
}
