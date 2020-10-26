package com.sunil.atas.twowaysslspringbootclient.controller;

import com.sunil.atas.twowaysslspringbootclient.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HelloClientController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello-client")
    public String hello() {
        return helloService.hello();
    }
}
