package io.stein.boundary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
class OpenApiTests {

    @Test
    void whenGetOpenApi_thenReturn200() {
        RestAssured.given()
                .when()
                .get("/openapi.yml")
                .then()
                .statusCode(200);
    }
}
