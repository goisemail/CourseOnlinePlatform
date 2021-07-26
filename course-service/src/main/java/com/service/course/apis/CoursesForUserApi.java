package com.service.course.apis;

import com.mongodb.MongoException;
import com.service.course.dao.CartDao;
import com.service.course.dao.CartStage;
import com.service.course.dtos.Cart;
import com.service.course.dtos.Courses;
import com.service.course.exception.CoursesRunTimeException;
import com.service.course.handler.CoursesHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/courses")
public class CoursesForUserApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger( CoursesForUserApi.class );

    private final CoursesHandler handler;

    @Autowired
    public CoursesForUserApi(CoursesHandler coursesHandler)
    {
        this.handler = coursesHandler;
    }

    //This API gives the availability of courses
    @GetMapping(path = "/available")
    @ResponseBody
    public List<Courses> getCourses()
    {
        LOGGER.info(" courses available API");

        List<Courses> courses = handler.getCourses();

        return courses;
    }

    //This API is register a course
    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity registerACourse(@RequestBody Cart cart) throws Exception
    {
        LOGGER.info("Course registering  API");

        ResponseEntity entity;
        LocalDateTime now = LocalDateTime.now();

        try
        {
            handler.validation(cart);

            cart = handler.saveCart(cart, now, CartStage.IN_PROGRESS );

            entity = new ResponseEntity(cart, HttpStatus.OK );
        }
        catch (MongoException e)
        {
            entity = new ResponseEntity( e.getMessage(), HttpStatus.CONFLICT );
        }
        catch (CoursesRunTimeException e)
        {
            entity = new ResponseEntity( e.getMessage(), HttpStatus.CONFLICT );
        }
        catch (Exception e)
        {
            entity = new ResponseEntity( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return entity;
    }

    //This API is for payment and blocking the seats for user
    @PostMapping(path = "/pay")
    @ResponseBody
    public ResponseEntity makePayment( @RequestBody Cart cart) throws Exception
    {
        LOGGER.info(" makePayment available API");
        ResponseEntity entity;
        Cart updated;

        try
        {
            CartDao cartDao = handler.getCart(cart);

            updated = handler.processPayment(cartDao, cart);

            handler.registerCourseForUser( cart );

            entity = new ResponseEntity( updated, HttpStatus.OK );
        }
        catch (CoursesRunTimeException e)
        {
            entity = new ResponseEntity( e.getMessage(), HttpStatus.CONFLICT );
        }
        catch (Exception e)
        {

            entity = new ResponseEntity( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return entity;
    }
}
