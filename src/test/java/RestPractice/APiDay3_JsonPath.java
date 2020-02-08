package RestPractice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class APiDay3_JsonPath {

   String url = "http://3.89.115.0:1000/ords/hr/";

   @Test
    public void TestCountFromResponseBody (){
                      given().accept(ContentType.JSON).
                              when().get(url+"countries")
                    .then().assertThat().statusCode(200)
                    .and().assertThat().contentType(ContentType.JSON)
                    .and().assertThat().body("items.region_id", hasSize(4))
                    .and().assertThat().body("items.region_name", Matchers.hasItems("Americas", "Asia"));

   }


      @Test
               public void TestWithQueryParameterAndList(){
                given().accept(ContentType.JSON).and().param("limit",100)
               .get(url+"employees")
               .then().assertThat().statusCode(200)
               .and().assertThat().contentType(ContentType.JSON)
               .and().assertThat().body("items.employee_id", Matchers.hasSize(100));


   }

        @Test
                public void TestWithPathParam(){
                given().contentType(ContentType.JSON)
                        .params("limit", 200)
                        .and().pathParams("employee_id" ,110)
                        .when().get(url+"employees/{employee_id}")
                        .then().statusCode(200)
                        .and().contentType(ContentType.JSON)
                        .and().assertThat().body("employee_id", Matchers.equalTo(110),
                                                    "first_name", Matchers.is("John"),
                                                        "last_name", equalTo("Chen"));


   }

         @Test
         public void TestWithJSONPath(){

             Map<String, Integer> rParamMap = new HashMap<>();

            Response response = given().accept(ContentType.JSON) //header
                    .and().params(rParamMap)                     //query param/request param
                    .and().pathParams("employee_id",177)  //path param
                    .when().get(url+"employees/{employee_id}");

             JsonPath json = response.jsonPath(); // get json body and assign to JsonPath object
             System.out.println(json.getInt("employee_id"));
             System.out.println(json.getString("last_name"));
             System.out.println(json.getString("job_id"));
             System.out.println(json.getString("links[0].href"));   // get specific element from array

             List <String> hrefs = json.getList("links.href");
             hrefs.forEach(e-> System.out.println(e));


         }
         @Test
        public void getJsonPathWtihList() {
             Map<String, Integer> rParamMap = new HashMap<>();
             rParamMap.put("limit", 100);
             Response response = given().accept(ContentType.JSON) //header
                     .and().params(rParamMap)                     //query param/request param
                     .when().get(url + "employees/");

             Assert.assertEquals(response.statusCode(), 200);
             JsonPath json = response.jsonPath();

             // JsonPath json2 = new JsonPath(response.asString()); // another way
             List<Integer> empIds = json.getList("items.employee_id");
             System.out.println(empIds);
             Assert.assertEquals(empIds.size(), 100);
             List<String> emails = json.getList("items.email");
             System.out.println(emails);
             Assert.assertEquals(emails.size(), 100);

             List<Integer> empIds2 = json.getList("items.findAll{it.employee_id>150}.employee_id");
             System.out.println(empIds2);

             List <String> strlst = json.getList("items.findAll{it.salary>7000}.last_name");
             System.out.println(strlst);

         }



}

