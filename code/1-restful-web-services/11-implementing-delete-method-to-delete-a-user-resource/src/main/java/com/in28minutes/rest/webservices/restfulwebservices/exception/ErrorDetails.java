package com.in28minutes.rest.webservices.restfulwebservices.exception;


import java.time.LocalDate;

public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }


}
