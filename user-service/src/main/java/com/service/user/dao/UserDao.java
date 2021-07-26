package com.service.user.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user")
public class UserDao
{

    @Indexed(unique = true)
    private String userName;

    private String password;

    private List<String> courseId;




}
