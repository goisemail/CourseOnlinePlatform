package com.service.course.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursesRunTimeException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private String message;

    public CoursesRunTimeException(String message) {
        this.message = message;
    }
}
