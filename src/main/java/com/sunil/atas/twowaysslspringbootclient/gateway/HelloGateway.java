package com.sunil.atas.twowaysslspringbootclient.gateway;

import com.sunil.atas.twowaysslspringbootclient.model.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelloGateway {

    @Value("${server.gateway.hello.url}")
    private String helloServiceUrl;

    @Autowired
    @Qualifier("sslRestTemplate")
    private RestTemplate restTemplate;

    public Hello getHello() {
        Hello hello = restTemplate.getForObject(helloServiceUrl, Hello.class);
        return hello;
    }
}
