package RestPractice.API_OWN_PRACTICE;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pojo {


    private String name;
    private String title;



    @JsonProperty("source")
    public void setName(Map<String, Object> source) {
        this.name = String.valueOf(source.get("name"));

    }

    public String getName(){

        return name;
    }


    public void setTitle (String title){

        this.title = String.valueOf(title);
    }


    public String getTitle (){

        return title;

    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", title='" + title + '\'' +
                        '}';
    }

}



