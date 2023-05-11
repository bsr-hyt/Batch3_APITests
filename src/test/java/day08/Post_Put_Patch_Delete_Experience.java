package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Post_Put_Patch_Delete_Experience {


    String email = "ceviz@gmail.com";
    String password = "Ceviz123456";

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }



    @Test
    public void createExperience() {

        String jsonBody="{\n" +
                "  \"job\": \"Junior Developer\",\n" +
                "  \"company\": \"Kraftech\",\n" +
                "  \"location\": \"Istanbul\",\n" +
                "  \"fromdate\": \"2015-01-01\",\n" +
                "  \"todate\": \"2016-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and()
                .header("token",Authorization.getToken())
                .and()
                .body(jsonBody)
                .when().log().all()
                .post("/experience/add").prettyPeek();

        int id = response.path("id");

    }

    @Test
    public void updateExperience() {
        //experience/updateput

        /**{
         "job": "Junior Developer",
         "company": "Kraft Techex",
         "location": "USA",
         "fromdate": "2015-01-01",
         "todate": "2016-01-01",
         "current": "false",
         "description": "Updated"
         }*/

        Map<String,Object> experienceBody = new HashMap<>();
        experienceBody.put("job","Junior Developer");
        experienceBody.put("company","Google");
        experienceBody.put("location","NY");
        experienceBody.put("fromdate","2016-01-01");
        experienceBody.put("todate","2017-01-01");
        experienceBody.put("current","false");
        experienceBody.put("description","Updated");

        Response response = given().accept(ContentType.JSON)
                .and()
//                .header("token",Authorization.getToken())
                .headers(Authorization.getToken(email,password))
                .and()
                .queryParam("id", 889)
                .and()
                .body(experienceBody)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();
    }
}
