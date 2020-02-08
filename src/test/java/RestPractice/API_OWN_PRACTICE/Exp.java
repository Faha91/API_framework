package RestPractice.API_OWN_PRACTICE;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class Exp {




    static RequestSpecification request;
    static Response body;
    static JsonPath json;



    public static Object  returnTitle(String author){

        HashMap <String, Object> map = new HashMap<>();
        map.put("country", "US");
        map.put("category", "business");
        map.put("pageSize", 50);
        map.put("apiKey", "c0feb0bc81c74e9284814912f6ccaa4a");
        request = given().baseUri("https://newsapi.org/v2/top-headlines")
                .queryParams(map)
                .accept(ContentType.JSON);
        body = request.get("");
        json = body.jsonPath();
        List <Map<String, Object>> lst = json.getList("articles");
        try{
            for (Map <String, Object> map2: lst) {
                for (Map.Entry<String, Object> entry : map2.entrySet()) {
                    if (String.valueOf(entry.getValue()).equals(author)) {
                        return map2.get("title");
                    }
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

       return null;
    }


    public static String  returnTitle2(String name){

       List <Pojo> lst = json.getList("source", Pojo.class);

        for (int  i = 0; i < lst.size(); i++){
            if (lst.get(i).getName().equals(name)){
                return lst.get(i).getTitle();
            }
        }





        return null;
    }



    public static void main (String[] args) {
      // System.out.println(returnTitle2("CNN"));
        System.out.println(returnTitle("Gary Gastelu"));


    }




//    Please write an app with following methods:
//
//    One method will accept author name, make a GET request to this endpoint: https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c0feb0bc81c74e9284814912f6ccaa4a  endpoint, and return title(s) belong to that author.
//    One method return all articles if  source[‘id’] is not null.
//    This app should have exception handling.
//    Write unit tests for method in #1 and #2
}

