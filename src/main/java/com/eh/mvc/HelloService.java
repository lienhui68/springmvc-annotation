package com.eh.mvc;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String prefix) {
        return prefix + " servlet3.0+ & springMvc";
    }
}
