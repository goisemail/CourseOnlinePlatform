package com.service.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    private String movieName;
    private Integer hall;
    private Integer slot;
    private Set<Integer> seats;

}
