package com.service.course.repository;

import com.service.course.dao.CoursesDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursesRepository extends MongoRepository<CoursesDao, String>
{
    CoursesDao findByName(String  name);
}
