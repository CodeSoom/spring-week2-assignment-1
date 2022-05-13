package com.codesoom.assignment.controllers.validations;

import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class RequestBodyValidation {
    private final TaskRequestDto body;

    public RequestBodyValidation(TaskRequestDto body) {
        this.body = body;
    }


    public void validate() {
        if (body.getTitle() == null || Objects.equals(body.getTitle(), "")
                || Objects.equals(body.getTitle(), " ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title에 입력된 값이 유효하지 않습니다. Null, '', ' '은 유효한 값이 아닙니다.");
        }
    }
}
