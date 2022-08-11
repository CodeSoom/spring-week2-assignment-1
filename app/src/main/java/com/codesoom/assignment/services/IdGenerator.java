package com.codesoom.assignment.services;

/**
 * Id 타입을 지정하여 구현되는 인터페이스 입니다
 * @param <T>
 */
public interface IdGenerator<T> {

    T generate();
}
