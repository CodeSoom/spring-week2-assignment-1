package com.codesoom.assignment.models;

import javax.validation.constraints.NotEmpty;

public class Task {

  private Long id;

  @NotEmpty
  private String title;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String toString() {
    return "(" + id + ", " + title + '}';
  }
}
