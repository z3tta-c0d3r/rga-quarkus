package com.quarkus.rga.bean;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloWorldService {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
