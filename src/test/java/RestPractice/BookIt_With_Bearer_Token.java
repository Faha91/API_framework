package RestPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookIt_With_Bearer_Token {


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
        //RestAssured.port = 8000;
        //RestAssured.basePath = "/api";
        // above will generate a BASE REQUEST URL OF http://52.23.254.102:8000/api
    }

    @Test
    public void getTokenTest(){

        given()
                .queryParam("email","emaynell8f@google.es")
                .queryParam("password","besslebond").
        when()
                 .get("/sign").
        then()
                .log().all()
                .statusCode(200)
                .body("accessToken" ,notNullValue()
                ) ;



    }


    @Test
    public void getAllRoom_Test(){

        String newToken = generateTokenUtility();
        given()
                .header("Authorization","Bearer " + newToken).
        when()
                .get("/api/rooms").
        then()
                .statusCode(200);



    }



    public String generateTokenUtility(){

        String token = given()
                .queryParam("email","emaynell8f@google.es")
                .queryParam("password","besslebond").
                when()
                .get("/sign")
                .jsonPath().getString("accessToken");

        return token ;
    }





}