package com.quarkus.rga.Controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrquestratorResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/helloworld")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}