package com.codesoom.assignment.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 식별자를 가지고 있는 작가입니다.
 */
@XmlRootElement
public class Author {
    private Long id;
    private String name;

    public Author() {
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
