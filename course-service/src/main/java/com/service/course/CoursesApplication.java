package com.service.course;

import com.service.course.handler.CoursesHandler;
import com.service.course.handler.CoursesAdministrationHandler;
import com.service.course.mapper.Mapper;
import com.service.course.adapter.PaymentAdapter;
import com.service.course.repository.CartRepository;
import com.service.course.repository.CoursesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

	@Bean
	public PaymentAdapter paymentHandler()
	{
		PaymentAdapter paymentAdapter = new PaymentAdapter();
		return paymentAdapter;
	}

	@Bean
	public Mapper mapper()
	{
		Mapper mapper = new Mapper();
		return mapper;
	}

	@Bean
	public CoursesAdministrationHandler coursesAdministrationHandler()
	{
		CoursesAdministrationHandler handler = new CoursesAdministrationHandler();
		return handler;
	}

	@Bean
	public CoursesHandler coursesHandler(CoursesRepository coursesRepository, CartRepository cartRepository, PaymentAdapter paymentAdapter, Mapper mapper)
	{
		CoursesHandler handler = new CoursesHandler(coursesRepository, cartRepository, paymentAdapter, mapper);

		return handler;
	}

}
