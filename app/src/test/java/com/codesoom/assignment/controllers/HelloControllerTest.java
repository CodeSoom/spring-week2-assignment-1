package com.codesoom.assignment.controllers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {
    @Test
    void sayHello() {
        HelloController controller = new HelloController();
        assertEquals("Hello, world!", controller.sayHello());
        assertThat(controller.sayHello()).isEqualTo("Hello, world!");
    }
}