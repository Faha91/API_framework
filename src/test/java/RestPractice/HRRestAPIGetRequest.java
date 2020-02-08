package RestPractice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;


public class HRRestAPIGetRequest {
/*



 */
    @Test
    public void simpleGet(){

                 when()
                .get("http://3.89.115.0:1000/ords/hr/employees")
                .then().statusCode(200);


    }

    @Test
    public void printResponse(){
    when().get("http://3.89.115.0:1000/ords/hr/countries").
         body().prettyPrint();

    }

    @Test
    public void getWithHeaders() {
        with().accept(ContentType.JSON).
                get("http://3.89.115.0:1000/ords/hr/countries/US").
                then().statusCode(200);
    }

      @Test
      public void negativeGetTest(){

         Response response =  when().get("http://3.89.115.0:1000/ords/hr/employees/44444");
          Assert.assertTrue(response.statusCode()==404);
          Assert.assertTrue(response.asString().contains("Not Found"));
          response.prettyPrint();
        }


        @Test
    public void verifyContentTypeWithAssertThat(){
         given().accept(ContentType.JSON)
         .when().get("http://3.89.115.0:1000/ords/hr/employees/100").
                 then().assertThat().statusCode(200).
                 and().contentType(ContentType.JSON);
        }

        @Test
    public void verifyFirstName(){

            given().accept(ContentType.JSON)
                    .when().get("http://3.89.115.0:1000/ords/hr/employees/100")
                    .then().statusCode(200).and().
                    contentType(ContentType.JSON).
                    assertThat().body("first_name", Matchers.equalTo("Steven"))
                    .and().assertThat().body("salary", Matchers.is(24000))
            .and().assertThat().body("employee_id", Matchers.equalTo(100));

        }





}
