package com.codesoom.assignment.interfaces;

public interface ITask {
    long id();

    String title();

    void updateId(long id);

    void updateTitle(String title);
}
