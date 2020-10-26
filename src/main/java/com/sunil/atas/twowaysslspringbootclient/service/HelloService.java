package com.sunil.atas.twowaysslspringbootclient.service;

import com.sunil.atas.twowaysslspringbootclient.gateway.HelloGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private HelloGateway helloGateway;

    public String hello() {
        return helloGateway.getHello();
    }
}
