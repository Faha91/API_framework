package RestPractice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;
import static io.restassured.RestAssured.given;
public class APIMuradilDay5 {

    String url = "http://3.89.115.0:1000/ords/hr/";


    @Test
    public void simpleGetRequest(){
        given().log().all().accept(ContentType.JSON).contentType(ContentType.JSON)
                .and().pathParams("employee_id", 100)
                .when().get(url+"employees/{employee_id}").prettyPrint();
    }

    @Test
    public void Assignment(){

        Map <String, Object> map2 = new HashMap<>();
        map2.put("employee_id", 11111);
        map2.put("first_name", "Fakhriddin");
        map2.put("last_name", "Kamardinov");
        map2.put("email", "kamardinov");
        map2.put("hire_date", "2003-06-17T04:00:00Z");
        map2.put("job_id", "AD_PRES");
        map2.put("phone_number", 112-222-3223);
        map2.put("commission_pct",null);
        map2.put("manager_id", null);
        map2.put("department_id", 90);
        Response response = given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(map2).when().
                post(url+"employees/");
        Assert.assertEquals(response.statusCode(), 201);
        JsonPath json = response.jsonPath();
        Map <String, Object> map = response.body().as(Map.class);

        Assert.assertEquals(map.get("last_name"), map2.get("last_name"));

        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParams("employee_id", 1234)
                .when().get(url+"employees/{employee_id}");
        response.prettyPrint();

    }


}
