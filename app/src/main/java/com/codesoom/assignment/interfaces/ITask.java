package com.codesoom.assignment.interfaces;

public interface ITask {
    Long id();

    String title();

    void updateId(Long id);

    void updateTitle(String title);
}
