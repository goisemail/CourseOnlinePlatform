package com.service.course.mapper;

import com.service.course.dao.CartDao;
import com.service.course.dao.CoursesDao;
import com.service.course.dtos.Cart;
import com.service.course.dtos.Courses;

import java.util.UUID;

public class Mapper {

    public CartDao toDao(Cart cart)
    {
        CartDao cartDao = new CartDao();

        cartDao.setCourses(cart.getCourses());
        cartDao.setId(cart.getId());
        cartDao.setInitiated(cart.getInitiated());
        cartDao.setPayment(cart.getPayment());
        cartDao.setStage(cart.getStage());
        cartDao.setUserName(cart.getUserName());

        return cartDao;
    }

    public Cart toModel(CartDao dao)
    {
        Cart cart = new Cart();

        cart.setCourses(dao.getCourses());
        cart.setId(dao.getId());
        cart.setInitiated(dao.getInitiated());
        cart.setPayment(dao.getPayment());
        cart.setStage(dao.getStage());
        cart.setUserName(dao.getUserName());

        return cart;
    }

    public Courses toModel(CoursesDao coursesDao)
    {
        Courses courses = new Courses(coursesDao.getId(), coursesDao.getName(), coursesDao.getDescription(), coursesDao.getSyllabus());

        return courses;
    }


    public CoursesDao toDao(Courses courses )
    {
        CoursesDao coursesDao = new CoursesDao();
        coursesDao.setDescription(courses.getDescription());
        coursesDao.setId(courses.getId());
        coursesDao.setName(courses.getName());
        coursesDao.setSyllabus(courses.getSyllabus());

        return coursesDao;
    }
}
