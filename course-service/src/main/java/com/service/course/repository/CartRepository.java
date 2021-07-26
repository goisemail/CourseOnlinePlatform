package com.service.course.repository;

import com.service.course.dao.CartDao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<CartDao, String>
{
    List<CartDao> findByUserName(String userName);
}
