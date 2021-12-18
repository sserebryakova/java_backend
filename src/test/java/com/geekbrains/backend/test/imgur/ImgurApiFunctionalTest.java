package com.geekbrains.backend.test.imgur;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends ImgurApiAbstractTest {

    private static String CURRENT_IMAGE_ID;
    private final String USER_NAME = "sserebryakova";

    @Test
    @Order(1)
    void getAccountBase() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.id", is (157951961))
                .log()
                .all()
                .when()
                .get("account/" + USER_NAME);
    }

    @Test
    @Order(2)
    void getAccountGalleryProfile() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.account_url", is("sserebryakova"))
                .body("data.email", is("sserebryakova@yandex.ru"))
                .log()
                .all()
                .when()
                .get("account/" + USER_NAME + "/" + "settings");
    }

    @Test
    @Order(3)
    void postImage() {
        CURRENT_IMAGE_ID = given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("puppy.jpg"))
                .formParam("name", "Picture")
                .formParam("title", "The best picture!")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.size", is(6399))
                .body("data.type", is("image/jpeg"))
                .body("data.name", is("Picture"))
                .body("data.title", is("The best picture!"))
                .log()
                .all()
                .when()
                .post("upload")
                .body()
                .jsonPath()
                .getString("data.id");
    }

    @Test
    @Order(4)
    void postUpdateImageInformation() {
        given()
                .spec(requestSpecification)
                .formParam("description", "puppy")
                .formParam("title", "Puppy!")
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data", is(true))
                .log()
                .all()
                .when()
                .post("image/" + CURRENT_IMAGE_ID);
    }

    @Test
    @Order(5)
    void getImage() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data.title", is("Puppy!"))
                .body("data.description", is("puppy"))
                .body("data.type", is("image/jpeg"))
                .body("data.size", is(6399))
                .log()
                .all()
                .when()
                .get("image/" + CURRENT_IMAGE_ID);
    }

    @Test
    @Order(6)
    void postFavoriteAnImage() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data", is("favorited"))
                .log()
                .all()
                .when()
                .post("image/" + CURRENT_IMAGE_ID + "/" + "favorite");
    }

    @Test
    @Order(7)
    void delImage() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(responseSpecification)
                .body("data", is(true))
                .log()
                .all()
                .when()
                .delete("image/" + CURRENT_IMAGE_ID);
    }
}