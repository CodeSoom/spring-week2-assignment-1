package com.codesoom.assignment.model;

import org.apache.logging.log4j.util.Strings;

public class UpdateTask {

    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "UpdateTask{" +
                "title='" + title + '\'' +
                '}';
    }
}
