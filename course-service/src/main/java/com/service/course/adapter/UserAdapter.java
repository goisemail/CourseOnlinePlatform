package com.service.course.adapter;

import com.service.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.service.course.constants.ErrorMessages.CART_FAILURE;

public class UserAdapter {

    public void register(String userName, String courseId) throws Exception
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity;

        User user = new User(userName, Arrays.asList(courseId) );
        try
        {
            //TODO: get this url from properties
            responseEntity = restTemplate.postForEntity("http://localhost:8889/user/addCourses", user, String.class);
            if( !responseEntity.getStatusCode().is2xxSuccessful() )
            {
                throw new Exception(CART_FAILURE);
            }
        }
        catch (Exception e)
        {
            throw new Exception(CART_FAILURE);
        }
    }
}
