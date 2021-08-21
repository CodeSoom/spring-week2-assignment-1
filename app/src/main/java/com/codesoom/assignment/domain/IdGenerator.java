package com.codesoom.assignment.domain;

@FunctionalInterface
public interface IdGenerator<T> {
    T generate(Object source);
}
