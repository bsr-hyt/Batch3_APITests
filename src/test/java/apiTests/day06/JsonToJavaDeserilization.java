package apiTests.day06;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class JsonToJavaDeserilization {

    //*****Test with hard assertion*****
    @Test
    public void test1() {
        //TASK
        //base url = https://gorest.co.in/
        //end point = /public/v2/users
        //path parameter = {id} --> 1366811
        //send a get request with the above credentials
        //parse to Json object to java collection
        //verify that the body below
    /*
    {
        "id": 1366811,
        "name": "Girja Khan",
        "email": "girja_khan@borer.test",
        "gender": "female",
        "status": "inactive"
    }
     */

        Response response = RestAssured
                .given()
                .when()
                .get("https://gorest.co.in/public/v2/users/1366811");

        //verify status code
        Assert.assertEquals(response.statusCode(), 200);

        //make verification with path method
        //verify id
        int actualId = response.path("id");
        int expectedId = 1366811;
        Assert.assertEquals(actualId, expectedId);
        //verify name
        String actualName = response.path("name");
        String expectedName = "Girja Khan";
        Assert.assertEquals(actualName, expectedName);
        //verify email
        String actualEmail = response.path("email");
        String expectedEmail = "girja_khan@borer.test";
        Assert.assertEquals(actualEmail, expectedEmail);

        //make verification with as()method
        //de-serilization --> json ı javaya çevirmek/dönüştürmek
        Map<String, Object> map = response.as(Map.class);
        System.out.println("map.get(\"id\") = " + map.get("id"));
        System.out.println("map.get(\"name\") = " + map.get("name"));
        System.out.println("map.get(\"email\") = " + map.get("email"));
        System.out.println("map.get(\"gender\") = " + map.get("gender"));
        System.out.println("map.get(\"status\") = " + map.get("status"));
        //verify id
        double actualIdMap = (double) map.get("id");
        Assert.assertEquals(actualIdMap,expectedId);
        //verify name
        String actualNameMap = (String) map.get("name");
        Assert.assertEquals(actualNameMap,expectedName);
        //verify email
        String actualEmailMap = (String) map.get("email");
        Assert.assertEquals(actualEmailMap,expectedEmail);
        //verify gender
        String actualGnederMap = (String) map.get("gender");
        String expectedGender = "female";
        Assert.assertEquals(actualGnederMap,expectedGender);
        //verify status
        String actualStatusMap = (String) map.get("status");
        String expectedStatus = "inactive";
        Assert.assertEquals(actualStatusMap,expectedStatus);
    }


    //*****Test with soft assertion*****

    @Test
    public void test2() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://gorest.co.in/public/v2/users/1366811");

        Map<String,Object> map = response.as(Map.class);
        double actualIdMap = (double) map.get("id");
        String actualNameMap = (String) map.get("name");
        String actualEmailMap = (String) map.get("email");
        String actualGnederMap = (String) map.get("gender");
        String actualStatusMap = (String) map.get("status");

        double expectedId = 1366811.0;
        String expectedName = "Girja Khan";
        String expectedEmail = "girja_khan@borer.test";
        String expectedGender = "female";
        String expectedStatus = "inactive";

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualIdMap,expectedId);
        softAssert.assertEquals(actualNameMap,expectedName);
        softAssert.assertEquals(actualEmailMap,expectedEmail);
        softAssert.assertEquals(actualGnederMap,expectedGender);
        softAssert.assertEquals(actualStatusMap,expectedStatus);

        softAssert.assertAll();

        //Not: Soft assertion'da hard assertiondan farklı olarak, geçip geçmediğini bir yere logluyor.
        //assertionun hepsini birden softAssert.assertAll(); da yapıyor.
        //Böylece hatadan sonrasındaki assertionlarında geçip geçmediğini öğrenebiliyoruz.
    }
}
