package fi.csc.data;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OutageResourceTest {

     @Test
    void testOutageEndpoint() {
        given()
          .when().get("/v1/outage")
          .then()
             .statusCode(200);
    }

    @Test
    void testOutagelinkEndpoint() {
        given()
          .when().get("/v1/outage/links")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }


}
