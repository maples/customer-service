package org.acme.customer;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class CustomerResourceTest {

    @Inject
    CustomerService customerService;

    UUID customerCreated;

    @BeforeEach
    public void setUp(){
        var customer = new CreateCustomerRequest("Test jUnit", "test@mail.com", "+55119905554");
        customerCreated  = customerService.add(customer);
    }

    @Test
    public void findTest(){
        given()
        .when()
        .get("/customers/{uuid}", customerCreated)
        .then()
        .statusCode(200)
        .body("name", is("Test jUnit"));
    }

    @Test
    public void addTest(){
        var jsonCustomer = """
                 {
                    "name": "Test Unit",
                    "email": "test@mail.com",
                    "phoneNumber": "+551198105045"
                 }
                """;
        given()
        .body(jsonCustomer)
        .header("Content-Type", "application/json")
        .when()
        .post("/customers")
        .then()
        .statusCode(201);
    }
    
}
