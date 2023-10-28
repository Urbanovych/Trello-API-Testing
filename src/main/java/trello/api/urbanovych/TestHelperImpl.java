package trello.api.urbanovych;

import org.springframework.web.client.RestTemplate;
import trello.api.urbanovych.endpoints.BoardsEndpoint;
import trello.api.urbanovych.endpoints.ListsEndpoint;

import java.util.Properties;

import static trello.api.urbanovych.properties.PropertiesHelper.getPropertiesFromPath;

public class TestHelperImpl implements TestHelper {

    private final RestTemplate restTemplate = new RestTemplate();
    Properties trelloAccess = getPropertiesFromPath("src/main/resources/trelloKeys.properties");
    private final String key = trelloAccess.getProperty("key");
    private final String token = trelloAccess.getProperty("token");

    // BOARDS
    public String getBoards() {
        return BoardsEndpoint.getBoards(restTemplate, key, token);
    }

    // LISTS
    public String postNewList(String name, String boardId) {
        return ListsEndpoint.postNewList(restTemplate, name, boardId, key, token);
    }

}
