package com.codesoom.assignment.dto;

import com.codesoom.assignment.model.Task;

public class TaskDto {

    public static class InsertReq {
        private long id;

        private String title;

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "InsertReq{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class UpdateReq {
        private String title;

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "UpdateReq{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    public static class TaskParam {

        private final long id;
        private final String title;

        public TaskParam(InsertReq request) {
            this.id = request.getId();
            this.title = request.getTitle();
        }

        public TaskParam(long id, UpdateReq request) {
            this.id = id;
            this.title = request.getTitle();
        }

        public Task toEitity() {
            return new Task(id, title);
        }

        @Override
        public String toString() {
            return "TaskParam{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}
