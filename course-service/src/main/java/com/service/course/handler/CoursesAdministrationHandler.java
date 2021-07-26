package com.service.course.handler;

import com.service.course.dao.CoursesDao;
import com.service.course.dtos.Courses;

public class CoursesAdministrationHandler
{


    public void validationChecks(CoursesDao coursesDao, Courses courses) throws Exception {

        if( coursesDao.getName().contentEquals(courses.getName()) )
        {
            throw new Exception("course name already exists");
        }
    }

    public void validationChecksForUpdation(CoursesDao coursesDao, Courses courses) throws Exception {

        if( !coursesDao.getId().contentEquals(courses.getId()) )
        {
            throw new Exception("course not exists");
        }
    }


}
