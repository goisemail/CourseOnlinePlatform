package com.service.course.apis;

import com.service.course.dao.CoursesDao;
import com.service.course.dtos.Courses;
import com.service.course.exception.CoursesRunTimeException;
import com.service.course.handler.CoursesAdministrationHandler;
import com.service.course.mapper.Mapper;
import com.service.course.repository.CoursesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.service.course.constants.ErrorMessages.COURSE_CONFLITS;

@RestController
@RequestMapping(path = "/admin/courses")
public class CoursesAdministrationApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger( CoursesAdministrationApi.class );

    @Autowired
    CoursesRepository coursesRepository;


    @Autowired
    CoursesAdministrationHandler handler;

    @Autowired
    Mapper mapper;

    //API to add by the admin. Separate access control has to do for this api
    @PostMapping("/add")
    @ResponseBody
    public Courses addCourses(@RequestBody Courses courses)
    {
        LOGGER.info("add courses api");
        try
        {
            CoursesDao existingCourses = coursesRepository.findByName( courses.getName() );

            handler.validationChecks(existingCourses, courses);
            existingCourses = mapper.toDao( courses );
            coursesRepository.save( existingCourses );
        }
        catch (Exception e)
        {
            throw new CoursesRunTimeException(COURSE_CONFLITS);
        }

        return courses;
    }

    @PutMapping("/update")
    @ResponseBody
    public Courses updateCourses(@RequestBody Courses courses)
    {
        LOGGER.info("add courses api");
        try
        {
            CoursesDao existingCourses = coursesRepository.findById( courses.getId() ).get();

            handler.validationChecksForUpdation(existingCourses, courses);
            existingCourses = mapper.toDao( courses );
            coursesRepository.save( existingCourses );
        }
        catch (Exception e)
        {
            throw new CoursesRunTimeException(COURSE_CONFLITS);
        }

        return courses;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Courses deleteCourses(@RequestBody Courses courses)
    {
        LOGGER.info("add courses api");
        try
        {
            CoursesDao existingCourses = coursesRepository.findById( courses.getId() ).get();
            coursesRepository.delete( existingCourses );
        }
        catch (Exception e)
        {
            throw new CoursesRunTimeException("Exception While deleting...");
        }

        return courses;
    }

}
