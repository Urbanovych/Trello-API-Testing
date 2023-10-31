package trello.api.urbanovych;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;
import trello.api.urbanovych.endpoints.BoardsEndpoint;
import trello.api.urbanovych.endpoints.ListsEndpoint;
import trello.api.urbanovych.objects.Board;
import trello.api.urbanovych.objects.BoardList;

import java.util.List;
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

    public Board updateBoardTitle(String boardId, String updatedName) {
        return BoardsEndpoint.updateBoard(restTemplate, boardId, key, token, updatedName);
    }

    public HttpStatusCode deleteBoard(String boardId) {
        return BoardsEndpoint.deleteBoard(restTemplate, boardId, key, token);
    }

    public List<BoardList> getListsOnBoard(String boardId) {
        return BoardsEndpoint.getListsOnBoard(restTemplate, boardId, key, token);
    }

    // LISTS
    public BoardList getList(String listId) {
        return ListsEndpoint.getList(restTemplate, listId, key, token);
    }

    public BoardList createList(String name, String boardId) {
        return ListsEndpoint.createList(restTemplate, name, boardId, key, token);
    }

    public BoardList updateListTitle(String listId, String updateName) {
        return ListsEndpoint.updateList(restTemplate, listId, updateName, key, token);
    }
}
