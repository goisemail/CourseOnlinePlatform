package com.service.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User
{

    @Indexed(unique = true)
    private String userName;


    private List<String> courseId;

}
