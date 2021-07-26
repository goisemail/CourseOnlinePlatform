package com.service.adapter.apis;

import com.service.adapter.dtos.Payment;
import org.springframework.http.ResponseEntity;

public interface PaymentApiInterface
{
    ResponseEntity pay(Payment payment) throws Exception;
}
