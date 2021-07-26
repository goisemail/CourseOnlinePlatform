package com.service.course.handler;

import com.service.course.dao.CartDao;
import com.service.course.dao.CartStage;
import com.service.course.dao.CoursesDao;
import com.service.course.dtos.Cart;
import com.service.course.dtos.Courses;
import com.service.course.dtos.Payment;
import com.service.course.exception.CoursesRunTimeException;
import com.service.course.mapper.Mapper;
import com.service.course.adapter.PaymentAdapter;
import com.service.course.repository.CartRepository;
import com.service.course.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.service.course.constants.ErrorMessages.POST_PAYMENT_CONFLICTS;

public class CoursesHandler
{

    private final CoursesRepository coursesRepository;

    private final CartRepository cartRepository;

    private final PaymentAdapter paymentAdapter;

    private final Mapper mapper;

    @Autowired
    public CoursesHandler(CoursesRepository coursesRepository, CartRepository cartRepository, PaymentAdapter paymentAdapter, Mapper mapper) {
        this.coursesRepository = coursesRepository;
        this.cartRepository = cartRepository;
        this.paymentAdapter = paymentAdapter;
        this.mapper = mapper;
    }

    public List<com.service.course.dtos.Courses> getCourses()
    {
        List<CoursesDao> coursesDaoList = coursesRepository.findAll();

        List<Courses> courses = coursesDaoList.stream()
                .map( mapper::toModel )
                .collect(Collectors.toList());

        return courses;
    }

    public Cart saveCart(Cart cart, LocalDateTime now, CartStage cartStage)
    {
        cart.setInitiated( now );
        cart.setStage(cartStage);
        CartDao dao = cartRepository.save( mapper.toDao(cart) );
        cart = mapper.toModel(dao);
        return cart;
    }

    public Cart processPayment(CartDao cartDao, Cart cart) throws Exception
    {
        Payment payment = paymentAdapter.capture( cart.getPayment() );
        cartDao.setPayment(payment);

        if (  isPaymentSuccess(payment)  )
        {
            cart = saveCart(cart, cart.getInitiated(), CartStage.CLOSED );
        }
        else
        {
            paymentAdapter.initiateRefund( cart.getPayment() );
            throw new CoursesRunTimeException(POST_PAYMENT_CONFLICTS);
        }
        return cart;
    }


    private boolean isPaymentSuccess(Payment payment )
    {
        return payment.isSuccess();
    }


    public void validation( Cart cart)
    {
        //TODO: For any validation
    }


    public CartDao getCart(Cart cart)
    {
        CartDao cartDao = cartRepository.findById(cart.getId()).get();
        return cartDao;
    }

    public Cart registerCourseForUser( Cart cart )
    {

        return null;
    }

}
