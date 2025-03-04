package com.awa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CalSyncResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/sync")
          .then()
             .statusCode(200)
             .body(is("Sincronización completada manualmente"));
    }

}