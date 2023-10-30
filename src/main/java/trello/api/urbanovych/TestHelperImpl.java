package trello.api.urbanovych;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;
import trello.api.urbanovych.endpoints.BoardsEndpoint;
import trello.api.urbanovych.endpoints.ListsEndpoint;
import trello.api.urbanovych.objects.Board;

import java.util.Properties;

import static trello.api.urbanovych.properties.PropertiesHelper.getPropertiesFromPath;

public class TestHelperImpl implements TestHelper {

    private final RestTemplate restTemplate = new RestTemplate();
    Properties trelloAccess = getPropertiesFromPath("src/main/resources/trelloKeys.properties");
    private final String key = trelloAccess.getProperty("key");
    private final String token = trelloAccess.getProperty("token");

    // BOARDS
    public Board getBoard(String boardId) {
        return BoardsEndpoint.getBoard(restTemplate, boardId, key, token);
    }

    public Board createBoard(String name) {
        return BoardsEndpoint.createBoard(restTemplate, name, key, token);
    }

    public Board updateBoard(String boardId, String updatedName) {
        return BoardsEndpoint.updateBoard(restTemplate, boardId, key, token, updatedName);
    }

    public HttpStatusCode deleteBoard(String boardId) {
        return BoardsEndpoint.deleteBoard(restTemplate, boardId, key, token);
    }

    // LISTS
    public String postNewList(String name, String boardId) {
        return ListsEndpoint.postNewList(restTemplate, name, boardId, key, token);
    }

}
