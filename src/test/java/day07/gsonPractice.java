package day07;

import apiPOJOTemplates.User;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class gsonPractice {

    //de-serilization
    //convert data from json to java

    //serilization
    //convert data java object(collection, class, etc.) to json

    //we will look into the answer of these questions


    @Test
    public void jsonToJava() {

        //create a json object
        Gson gson = new Gson();

        String userJsonBody = "{\n" +
                "    \"id\": 1513738,\n" +
                "    \"name\": \"Amish Khan\",\n" +
                "    \"email\": \"amish_khan@greenholt.test\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"inactive\"\n" +
                "}";

        System.out.println(userJsonBody);

        //de-serilization
        //json --> java
        Map<String,Object> map = gson.fromJson(userJsonBody, Map.class); //aslında as classı arka tarafta bu işlemi yapıyor.
        //see how map look like
        System.out.println(map);

        //de-serilization
        //json --> java (User)     (User clasından oluşturduğum bir objenin içerisine ordaki dataları at demek bu.)
        User user = gson.fromJson(userJsonBody, User.class);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getGender());
        System.out.println(user.getStatus());

    }

    @Test
    public void javaToJson() {
        //create a gson object
        Gson gson = new Gson();

        //create a user object with the following data
        //id --> 1
        //name --> Aslıhan
        //email --> aslıhan@gmail.com
        //gender --> female
        //status --> inactive

        User user = new User();
        user.setId(1);
        user.setName("Aslıhan");
        user.setEmail("aslıhan@gmail.com");
        user.setGender("female");
        user.setStatus("inactive");

        System.out.println(user);

        //serilization
        //java to json
        String jsonUser = gson.toJson(user);

        //see how jsonUser looks like
        System.out.println(jsonUser);
    }
}
