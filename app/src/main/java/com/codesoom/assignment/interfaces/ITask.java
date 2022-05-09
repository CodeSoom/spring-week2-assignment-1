package com.codesoom.assignment.interfaces;

public interface ITask {
    int id();

    String title();

    void updateId(int id);

    void updateTitle(String title);
}
