package com.quarkus.rga.Controller;

import com.quarkus.rga.bean.HelloWorldService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class OrquestratorResource {

    @Inject
    HelloWorldService helloWorldService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sayHello/{name}")
    public String hello(@PathParam String name) {
        return helloWorldService.sayHello(name);
    }

}