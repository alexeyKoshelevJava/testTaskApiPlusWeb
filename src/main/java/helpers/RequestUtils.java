package helpers;

import io.restassured.specification.RequestSpecification;
import pojo.SingleUser;

import static helpers.Specification.responseSpec;
import static io.restassured.RestAssured.given;

public class RequestUtils {
    public static SingleUser sendGet( RequestSpecification requestSpec) {
        return given()
                .spec(requestSpec)
                .when()
                .get()
                .then()
                .log().all()
                .spec(responseSpec())
                .extract().body().as(SingleUser.class);


    }
}
