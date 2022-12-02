package org.acme.customer;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;


@QuarkusTest
class CustomerResourceTest {

    @Inject
    CustomerService customerService;

    @Test
    void addTest(){
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
