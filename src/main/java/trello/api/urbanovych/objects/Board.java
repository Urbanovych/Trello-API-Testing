package trello.api.urbanovych.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

// Board POJO has a lot of parameters, cover only id and name.
public class Board {

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
