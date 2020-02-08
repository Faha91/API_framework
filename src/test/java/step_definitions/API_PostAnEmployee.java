package step_definitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import static io.restassured.RestAssured.*;
public class API_PostAnEmployee {
    RequestSpecification request;
    int employeeId;
    Response response;
    String url = "http://3.89.115.0:1000/ords/hr/";
    int empoyee_id;
    Map <String, Object> map2 = new HashMap<>();
    Map <String, Object> map ;
    @Given("Content type and Accept type is Json")
    public void content_type_and_Accept_type_is_Json() {
       request= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON);
    }

    @When("I post a new Employee with {string} id")
    public void i_post_a_new_Employee_with_id(String string) {
       if( string.equals("random")){
           employeeId = new Random().nextInt(9999);
       }else
           employeeId = Integer.parseInt(string);
        map2.put("employee_id", employeeId);
        map2.put("first_name", "Fakhriddin");
        map2.put("last_name", "Kamardinov");
        map2.put("email", "kamardinov");
        map2.put("hire_date", "2003-06-17T04:00:00Z");
        map2.put("job_id", "AD_PRES");
             response =  given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(map2).when().
                        post(url+"employees/");
    }

    @Then("Status code is {int}")
    public void status_code_is(int int1) {

        Assert.assertEquals(response.statusCode(), int1 );
    }

    @When("I send a GET request with {string} id")
    public void i_send_a_GET_request_with_id(String string) {

       response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and(). pathParams("employee_id", employeeId)
                .when().get(url+"employees/{employee_id}");

       map =  response.body().as(Map.class);
    }

    @Then("Employee JSON Response Data should match the posted JSON data")
    public void employee_JSON_Response_Data_should_match_the_posted_JSON_data() {

        Assert.assertEquals(map2.get("first_name"),map.get("first_name") );
        Assert.assertEquals(map2.get("employee_id"),map.get("employee_id") );
        Assert.assertEquals(map2.get("last_last"),map.get("last_name") );
        Assert.assertEquals(map2.get("email"),map.get("email") );

    }

}
