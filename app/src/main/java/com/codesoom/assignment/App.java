package com.codesoom.assignment;

import com.codesoom.assignment.application.TaskRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.GenericApplicationContext;


@SpringBootApplication
public class App {
    public String getGreeting() {
        return "Hello, world!";
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
