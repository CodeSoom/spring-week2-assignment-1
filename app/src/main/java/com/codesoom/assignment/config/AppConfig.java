package com.codesoom.assignment.config;

import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.repositories.TaskRepositoryImpl;
import com.codesoom.assignment.services.TaskService;
import com.codesoom.assignment.services.TaskServiceImpl;
import com.codesoom.assignment.utils.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public TaskRepository taskRepository() {
        return new TaskRepositoryImpl();
    }

    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

    @Bean
    public TaskService taskService() {
        return new TaskServiceImpl(taskRepository(), idGenerator());
    }
}
