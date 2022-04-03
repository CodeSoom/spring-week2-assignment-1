package com.codesoom.assignment.controllers.task;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 할 일과 관련된 컨트롤러 애노테이션 입니다.
 */
@CrossOrigin
@RequestMapping("/tasks")
@RestController
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TaskController {
}
