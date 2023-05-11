package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class Authorization {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void test1() {

        String email = "ceviz@gmail.com";
        String password = "Ceviz123456";

        Response response = RestAssured.given()
//                .accept(ContentType.MULTIPART)
                .accept(ContentType.ANY)
                .and()
                .formParam("email", email)
                .formParam("password", password)
                .and()
                .log().all()
                .when()
                .post("allusers/login");

        String token = response.path("token");
        System.out.println("token = " + token);
    }



    public static String getToken() {
        String email = "ceviz@gmail.com";
        String password = "Ceviz123456";

        Response response = RestAssured.given().accept(ContentType.MULTIPART)
                .and()
                .formParam("email", email)
                .formParam("password", password)
                .and()
                .log().all()
                .when()
                .post("allusers/login");

        String token = response.path("token");
        return token;
    }

    public static Map<String,Object> getToken(String email, String password){
        Response response = RestAssured.given().accept(ContentType.MULTIPART)
                .and()
                .formParam("email", email)
                .formParam("password", password)
                .and()
                .log().all()
                .when()
                .post("allusers/login");

        String token = response.path("token");

        Map<String,Object> authorization = new HashMap<>();
        authorization.put("token",token);
        return authorization;
    }

}
