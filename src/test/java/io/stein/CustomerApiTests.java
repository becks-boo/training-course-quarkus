package io.stein;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.instanceOf;

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
}
