package com.service.user.repository;

import com.service.user.dao.TokenDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<TokenDao, String>
{
    TokenDao findById(String id);
    //String findByToken(String token);
}
