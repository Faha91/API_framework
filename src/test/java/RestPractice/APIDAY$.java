package RestPractice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIDAY$ {

    String url = "http://3.89.115.0:1000/ords/hr/";



    @Test
    public void Assignment(){
      Response response =  given().accept(ContentType.JSON)
              .and().params("limit", 10)
                .when().get(url+"regions");
        JsonPath json = response.jsonPath();
        Assert.assertEquals(response.statusCode(),200);
        List<String> regions = json.getList("items.region_name");
        System.out.println(regions);

    }
}
/*
{
"region_id":5
"region_name" : "Fakhriddin

}
 */
