package com.codesoom.assignment.dto;

public class ErrorResponse {
    private  String messagee;

    public ErrorResponse(String message){
        this.messagee = message;
    }

    public String getMessagee() {
        return messagee;
    }
}
