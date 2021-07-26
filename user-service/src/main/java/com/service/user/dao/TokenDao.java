package com.service.user.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "token")
public class TokenDao
{
    @Id
    private String id;

    @Indexed
    private String userName;

    //TODO: token expiration
//    @Indexed(expireAfterSeconds = 0)
//    private LocalDateTime expiration;

    public TokenDao(String userName)
    {
        this.userName = userName;
    }
}
