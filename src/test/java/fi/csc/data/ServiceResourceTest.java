package fi.csc.data;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ServiceResourceTest {
    @Test
    void testServiceEndpoint() {
        given()
          .when().get("/v2/services/text")
          .then()
             .statusCode(200)
             ;
    }

}