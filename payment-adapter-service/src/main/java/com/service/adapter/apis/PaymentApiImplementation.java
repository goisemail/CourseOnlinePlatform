package com.service.adapter.apis;

import com.service.adapter.dtos.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping(path = "/payment")
public class PaymentApiImplementation implements PaymentApiInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger( PaymentApiImplementation.class );

    private RestTemplate restTemplate;

    @PostMapping
    @ResponseBody
    public ResponseEntity pay(@RequestBody Payment payment) throws Exception
    {
        LOGGER.info(" payment API");
        ResponseEntity entity = new ResponseEntity(HttpStatus.ACCEPTED);

        Random random = new Random();
        restTemplate = new RestTemplate();

        if( random.nextBoolean())
        {
            //TODO: add the url in property files
            entity = restTemplate.postForEntity("http://localhost:8080/gpay/", payment, Payment.class);
            if( !entity.getStatusCode().is2xxSuccessful() )
            {
                throw new Exception();
            }
        }
        return entity;
    }
}
