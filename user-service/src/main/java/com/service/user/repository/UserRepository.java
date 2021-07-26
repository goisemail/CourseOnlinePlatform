package com.service.user.repository;

import com.service.user.dao.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDao, String>
{
    UserDao findByUserName(String userName);
}
