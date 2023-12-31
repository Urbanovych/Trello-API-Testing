package trello.api.urbanovych.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

// List POJO has a lot of parameters, cover only id and name.
public class BoardList {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
