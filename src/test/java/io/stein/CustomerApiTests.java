package io.stein;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CustomerApiTests {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiTests.class);

    @Test
    void whenGetCustomers_thenOk() {
        given()
                .when()
                .get("/customers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", instanceOf(List.class));
    }

    @Test
    void whenPostCustomers_thenCreated() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                            {
                              "name": "Tom Mayer",
                              "birthdate": "2001-04-23",
                              "state": "active"
                            }
                        """)
                .accept(ContentType.JSON)
                .when()
                .post("/customers")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", is(equalTo("Tom Mayer")))
                .body("birthdate", is(equalTo("2001-04-23")))
                .body("state", is(equalTo("active")))
                .body("uuid", is(notNullValue()))
                .header("Location", is(notNullValue()));
    }
}
