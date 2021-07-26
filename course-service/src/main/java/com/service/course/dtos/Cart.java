package com.service.course.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.course.dao.CartStage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart
{
    private String id;

    private String userName;

    private Set<Courses> courses;

    private Payment payment;

    private CartStage stage;

    private LocalDateTime initiated;
}
