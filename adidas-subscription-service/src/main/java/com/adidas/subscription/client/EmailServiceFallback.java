package com.adidas.subscription.client;

import org.springframework.stereotype.Component;

import com.adidas.subscription.client.model.Email;
@Component
public class EmailServiceFallback implements EmailServiceFeignClient {
    @Override
    public Boolean checkEmail(final Email body) {
        return Boolean.TRUE;
    }

}
