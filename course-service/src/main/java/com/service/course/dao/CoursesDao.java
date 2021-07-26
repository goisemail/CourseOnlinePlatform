package com.service.course.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Courses")
public class CoursesDao implements Serializable
{

    @Id
    private String id;

    @Indexed( unique = true)
    private String name;

    private String description;

    private List<Object> syllabus;

    //Versioning helps in achieving data consistency
    @Version
    private Long version;

}
