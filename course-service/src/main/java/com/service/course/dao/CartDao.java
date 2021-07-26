package com.service.course.dao;

import com.service.course.dtos.Courses;
import com.service.course.dtos.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Cart")
public class CartDao
{
    @Id
    private String id;

    private String userName;

    private Set<Courses> courses;

    private Payment payment;

    private CartStage stage;

    private LocalDateTime initiated;
}
