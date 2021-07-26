package com.service.user.implementation;

import com.service.user.dao.TokenDao;
import com.service.user.dao.UserDao;
import com.service.user.model.User;
import com.service.user.repository.TokenRepository;
import com.service.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserApiImplementation
{

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity getToken(@RequestBody UserDao loginPayload) throws Exception
    {
        ResponseEntity entity;

        if( isUserValid(loginPayload) )
        {
            TokenDao tokenDao = new TokenDao(loginPayload.getUserName());
            tokenDao = tokenRepository.save(tokenDao);
            entity = new ResponseEntity(tokenDao.getId(), HttpStatus.OK);
        }
        else
        {
            entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }

    private boolean isUserValid(UserDao loginPayload) throws Exception
    {
        UserDao userDao =  userRepository.findByUserName( loginPayload.getUserName());

        //TODO: Encryption/decryption needed
        boolean flag =  userDao.getPassword().contentEquals( loginPayload.getPassword() ) ? Boolean.TRUE : Boolean.FALSE;

        return flag;
    }

    @PostMapping(path = "/validate")
    @ResponseBody
    public ResponseEntity findToken(@RequestBody String token) throws Exception
    {
        //TODO: Implementation with Cache for further improvement
        //TODO: section Handling to be improved
        ResponseEntity entity;
        TokenDao tokenDao = tokenRepository.findById(token);

        if (null == tokenDao)
        {
            entity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        else
        {
            entity = new ResponseEntity(token, HttpStatus.OK);
        }
        return entity;
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public UserDao createUser(@RequestBody UserDao loginPayload) throws Exception {
        UserDao savedUser;
        try
        {
            UserDao userDao = new UserDao();
            userDao.setUserName( loginPayload.getUserName() );
            //TODO: encryption
            userDao.setPassword( loginPayload.getPassword() );
            savedUser = userRepository.save(userDao);
        }
        catch (Exception e)
        {
            //TODO: proper Exception Handling
            throw e;
        }
        return savedUser;
    }

    @DeleteMapping(path = "/logout")
    public void logOut( RequestEntity requestEntity)
    {
        try
        {
            List<String> token = requestEntity.getHeaders().get("token");
            userRepository.delete(token.get(0));
        }
        catch (Exception e)
        {
            //TODO: proper Exception Handling
            throw e;
        }
    }

    @PutMapping(path = "/addCourses")
    public UserDao addCourses( User user)
    {
        UserDao userDao;
        try
        {
            userDao = userRepository.findByUserName(user.getUserName());
            List<String> coursesId = userDao.getCourseId();
            coursesId.addAll(user.getCourseId());
            userDao.setCourseId(coursesId);


            userDao = userRepository.save( userDao );
        }
        catch (Exception e)
        {
            //TODO: proper Exception Handling
            throw e;
        }
        return userDao;
    }


}
