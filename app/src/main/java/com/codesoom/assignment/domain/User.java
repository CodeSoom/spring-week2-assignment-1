package com.codesoom.assignment.domain;

/**
 * 식별자를 가지고 있는 유저입니다.
 */
public class User {
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 출력을 위한 메서드입니다.
     *
     * @return 식별자
     */
    public Long getId() {
        return id;
    }

    /**
     * 출력을 위한 메서드입니다.
     *
     * @return 유저명
     */
    public String getName() {
        return name;
    }
}
