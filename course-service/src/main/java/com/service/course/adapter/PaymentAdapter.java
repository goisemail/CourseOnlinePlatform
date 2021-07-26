package com.service.course.adapter;

import com.service.course.dtos.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.service.course.constants.ErrorMessages.CART_FAILURE;
import static com.service.course.constants.ErrorMessages.COURSE_CONFLITS;

public class PaymentAdapter
{
    public Payment capture(Payment payment) throws Exception
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.ACCEPTED);

        try
        {
            //TODO: get this url from properties
            responseEntity = restTemplate.postForEntity("http://localhost:8889/payment/", payment, Payment.class);
            if( !responseEntity.getStatusCode().is2xxSuccessful() )
            {
                throw new Exception(CART_FAILURE);
            }
        }
        catch (Exception e)
        {
            throw new Exception(CART_FAILURE);
        }

        return (com.service.course.dtos.Payment) responseEntity.getBody();
    }

    public void initiateRefund(Payment payment) throws Exception
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity;
        try
        {
            responseEntity = restTemplate.postForEntity("http://localhost:8080/payment/refund", payment, Payment.class);
            if( !responseEntity.getStatusCode().is2xxSuccessful() )
            {
                throw new Exception(COURSE_CONFLITS);
            }
        }
        catch (Exception e)
        {
            throw new Exception(COURSE_CONFLITS);
        }

    }
}
