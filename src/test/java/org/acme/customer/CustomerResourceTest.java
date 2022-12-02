package org.acme.customer;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class CustomerResourceTest {

    @Inject
    CustomerService customerService;

//    UUID customerCreated;
//
//    @BeforeEach
//    public void setUp(){
//        var customer = new CreateCustomerRequest("Test jUnit", "test@mail.com", "+55119905554");
//        customerCreated  = customerService.add(customer);
//    }
//
//    @Test
//    public void findTest(){
//        given()
//        .log()
//        .all()
//        .when()
//        .get("/customers/{uuid}", customerCreated)
//        .then()
//        .statusCode(200).extract().response();
//    }

    @Test
    public void addTest(){
        var jsonCustomer = """
                 {
                    "name": "Test Unit",
                    "email": "test@mail.com",
                    "phoneNumber": "+551198105045"
                 }
                """;
        ExtractableResponse<Response> response = given()
            .body(jsonCustomer)
            .header("Content-Type", "application/json")
            .when()
            .post("/customers")
            .then()
            .statusCode(201)
            .extract();


        String[] strings = response.header("Location").split("/");
        var uuid = strings[strings.length - 1];

        given()
            .header("Content-Type", "application/json")
            .when()
            .get("/customers/{uuid}", uuid)
            .then()
            .statusCode(200);

    }

}
