package io.stein.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.stein.test.shared.OutputCaptureExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
@ExtendWith(OutputCaptureExtension.class)
public class CustomerEventLoggerTests {

    @Test
    void whenCreateCustomer_thenLogEvent(OutputCaptureExtension.CapturedOutput output) {
        var response = given()
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
                .post("/customers");
        response
                .then()
                .statusCode(201)
                .header("Location", is(notNullValue()));
        var uuid = response.body().jsonPath().getString("uuid");

        assertThat(output)
                .containsPattern(String.format("(?i).*Customer created.*%s.*", uuid));
    }

}