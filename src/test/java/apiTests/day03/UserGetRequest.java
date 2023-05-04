package apiTests.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import  static org.testng.Assert.*; //static yaptık. --> artık Assert yazmama gerek yok.
import static io.restassured.RestAssured.*; // --> given() ı direkt yazabilirsin; class ismini kullanmana gerek yok.

public class UserGetRequest {

    /**  Class Task
     * Given accept type is JSON
     * When user send GET request to /Books
     * Then verify that response status code is 200
     * and body is JSON format
     * and response body contains "Richard E. Silverman"
     * URL = https://bookstore.toolsqa.com/BookStore/v1/Books
     * */

    String bookStoreBaseUrl = "https://bookstore.toolsqa.com/BookStore/v1";

    String exlabBaseUrl = "https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get(bookStoreBaseUrl + "/Books");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("Richard E. Silverman"));

    }

    @Test
    public void test2(){
        /**  Class Task
         * Get All Users
         * */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .and()
                .when()
                .get(exlabBaseUrl + "/allusers/alluser");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void headerTest(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 24)
                .when()
                .get(exlabBaseUrl + "/allusers/getbyid/{id}");

//        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        //verify the header
        assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");
       // assertEquals(response.header("Content-Length"),"968");
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //verify body
        assertTrue(response.body().asString().contains("mike@gmail.com"));
    }

}
