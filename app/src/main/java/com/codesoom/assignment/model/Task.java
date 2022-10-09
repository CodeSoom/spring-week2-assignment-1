package com.codesoom.assignment.model;

import org.apache.logging.log4j.util.Strings;

public class Task {

    public static final String NO_TITLE = "No Title";
    private long id;

    private String title;

    public Task(long id, String title) {
        this.id = id;
        this.title = initTitle(title);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = initTitle(title);
    }

    /**
     * 입력받은 타이틀을 검증해서 빈 값일 경우 NO_TITLE 의 문자열 값을 리턴한다
     * @param title 할 일 제목
     * @return 가공된 할 일 제목
     */
    public String initTitle(String title) {
        return Strings.isBlank(title) ? NO_TITLE : title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
