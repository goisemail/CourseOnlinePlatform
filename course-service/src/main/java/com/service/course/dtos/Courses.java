package com.service.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Courses {

    private String id;

    private String name;

    private String description;

    private List<Object> syllabus;

}
