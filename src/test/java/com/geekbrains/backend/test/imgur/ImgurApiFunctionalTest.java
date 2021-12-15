package com.geekbrains.backend.test.imgur;


import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends FunctionalTest {

    private static String TOKEN;
    private static Properties properties;

    @BeforeAll
    static void beforeAll() throws Exception {
        Properties properties = readProperties();
        RestAssured.baseURI = properties.getProperty("imgur-api-url");
        TOKEN = properties.getProperty("imgur-api-token");
    }

    @Test
    @Order(1)
    void getAccountBase() {
        String userName = "sserebryakova";
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.id", is(157951961))
                .log()
                .all()
                .when()
                .get("account/" + userName);
    }

    @Test
    @Order(2)
    void getAccountGalleryProfile() {
        String userName = "sserebryakova";
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.account_url", is("sserebryakova"))
                .body("data.email", is("sserebryakova@yandex.ru"))
                .body("success", is(true))
                .log()
                .all()
                .when()
                .get("account/" + userName + "/" + "settings");
    }

    @Test
    @Order(3)
    void postImage() {
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("image", getFileResource("puppy.jpg"))
                .formParam("name", "Picture")
                .formParam("title", "The best picture!")
                .log()
                .all()
                .expect()
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
        String imageHash = "xSFt7hd";
        given()
                .auth()
                .oauth2(TOKEN)
                .formParam("imageHash", "xSFt7hd")
                .formParam("description", "puppy")
                .formParam("title", "Puppy!")
                .log()
                .all()
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .log()
                .all()
                .when()
                .post("image/" + imageHash)
                .body()
                .jsonPath()
                .getString("data");
    }

    @Test
    @Order(5)
    void getImage() {
        String imageHash = "xSFt7hd";
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.title", is("Puppy!"))
                .body("data.description", is("puppy"))
                .body("data.type", is("image/jpeg"))
                .body("data.size", is(152140))
                .log()
                .all()
                .when()
                .get("image/" + imageHash);
    }

    @Test
    @Order(6)
    void postFavoriteAnImage() {
        String imageHash = "xSFt7hd";
        given()
                .auth()
                .oauth2(TOKEN)
                .formParam("imageHash", "xSFt7hd")
                .log()
                .all()
                .expect()
                .body("data", is("favorited"))
                .body("success", is(true))
                .log()
                .all()
                .when()
                .post("image/" + imageHash + "/" + "favorite")
                .body()
                .jsonPath()
                .getString("data");
    }

    @Test
    @Order(7)
    void delImage() {
        String imageHash = "xSFt7hd";
        given()
                .auth()
                .oauth2(TOKEN)
                .formParam("imageHash", "xSFt7hd")
                .log()
                .all()
                .expect()
                .body("data", is(true))
                .body("success", is(true))
                .log()
                .all()
                .when()
                .post("image/" + imageHash)
                .body()
                .jsonPath()
                .getString("data");
    }
}
