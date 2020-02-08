package RestPractice;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class APIDay3_GSON {
    String url = "http://3.89.115.0:1000/ords/hr/";




    @Test
    public void testWithHashMat(){

       Response response =  given().accept(ContentType.JSON)
                .when().get(url+"employees/120");
       HashMap <String, String> map = response.as(HashMap.class);
        System.out.println(map.keySet());
        System.out.println(map.values());
        Assert.assertEquals(map.get("employee_id"),120);
        Assert.assertEquals(map.get("job_id"), "ST_MAN");
    }

        @Test
        public void convertJsonToListOfMap() {

            Response response = given().accept(ContentType.JSON)
                    .when().get(url + "departments");
            //List<Map<String,String> > listOfMaps = response.as(ArrayList.class);
            List<Map> listOfMaps = response.jsonPath().getList("items", Map.class);
            Assert.assertEquals(listOfMaps.get(0).get("department_name"), "Administration");
            System.out.println(listOfMaps.get(0));


        }
}
