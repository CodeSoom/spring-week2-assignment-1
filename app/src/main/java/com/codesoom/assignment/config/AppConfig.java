package com.codesoom.assignment.config;

import com.codesoom.assignment.repository.MemoryTaskRepository;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public TaskRepository taskRepository() {
    return new MemoryTaskRepository();
  }
}
