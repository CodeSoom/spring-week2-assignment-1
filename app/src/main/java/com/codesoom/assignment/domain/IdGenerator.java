package com.codesoom.assignment.domain;

@FunctionalInterface
public interface IdGenerator {
    Long generate(Long source);
}
