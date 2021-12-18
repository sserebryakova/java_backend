package com.geekbrains.backend.test.imgur;

import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.internal.AuthenticationSpecificationImpl;
import io.restassured.specification.AuthenticationSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Properties;

import static org.hamcrest.Matchers.is;

public class ImgurApiAbstractTest extends FunctionalTest {

    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    static {
        try {
            Properties properties = readProperties();
            RestAssured.baseURI = properties.getProperty("imgur-api-url");
            String TOKEN = properties.getProperty("imgur-api-token");
            requestSpecification = new RequestSpecBuilder()
                    .setAuth(new OAuth2Scheme())
                    .build();
            AuthenticationSpecification auth = new AuthenticationSpecificationImpl(requestSpecification);
            requestSpecification = auth.oauth2(TOKEN);
            responseSpecification = new ResponseSpecBuilder()
                    .expectBody("success", is(true))
                    .expectStatusCode(200)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}