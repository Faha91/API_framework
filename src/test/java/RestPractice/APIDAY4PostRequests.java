package RestPractice;

import RestPractice.beans.Country;
import RestPractice.beans.CountryResponse;
import RestPractice.beans.Region;
import RestPractice.beans.RegionResponse;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class APIDAY4PostRequests {
    String url = "http://3.89.115.0:1000/ords/hr/";


    String requestJson = "{\"region_id\": 5,\"region_name\": \"Kamardinov's Region\"}";
    @Test
    public void postNewRegion(){
        Map requestMap = new HashMap();
        requestMap.put("region_id", 555);
        requestMap.put("region_name", "Kamardinov's");
    Response response = given().accept(ContentType.JSON)
                       .and().contentType(ContentType.JSON)
                       .and().body(requestMap)
                        .when().post(url+"regions/");
        System.out.println(response.statusLine());
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 201);
        Map responseMap = response.body().as(Map.class);
       // Assert.assertEquals(response,requestMap); do not work
        Assert.assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
        Assert.assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));

}


@Test
    public void postUsingPojo(){
    Region region = new Region();
    region.setRegion_id(9);
    region.setRegion_name("Fakha");

    Response response = given().log().all()
            .accept(ContentType.JSON).and()
            .and().contentType(ContentType.JSON)
            .and().body(region)
            .when().post(url+"regions/");
    Assert.assertEquals(response.statusCode(),201);
    RegionResponse responseRegion = response.body().as(RegionResponse.class);
    Assert.assertEquals(region.getRegion_id(), responseRegion.getRegion_id());
    Assert.assertEquals(region.getRegion_name(), responseRegion.getRegion_name());

}


@Test

    public void TestWithPojo2() {
    Country FahaLand = new Country();
    FahaLand.setCountry_id("FA");
    FahaLand.setCountry_name("Fahristan");
    FahaLand.setRegion_id(2);

    Response response = given().log().all()
            .accept(ContentType.JSON)
            .and().contentType(ContentType.JSON)
            .and().body(FahaLand)
            .when().post("http://3.89.115.0:1000/ords/hr/countries/");

    Assert.assertEquals(response.statusCode(), 201);

    CountryResponse coun = response.body().as(CountryResponse.class);

    Assert.assertEquals(FahaLand.getCountry_id(), coun.getCountry_id());
    Assert.assertEquals(FahaLand.getCountry_name(), coun.getCountry_name());
    Assert.assertEquals(FahaLand.getRegion_id(), coun.getRegion_id());


}

    @Test

    public void Test4(){
        Map <String,Object> map = new HashMap<>();
        map.put("id","200" );
         given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .and().pathParams("id",200).when().
                get(url+"employees/{id}").body().prettyPrint();



    }



}








