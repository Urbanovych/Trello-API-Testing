package trello.api.urbanovych;

import org.springframework.web.client.RestTemplate;
import trello.api.urbanovych.endpoints.BoardsEndpoint;
import trello.api.urbanovych.endpoints.ListsEndpoint;

public class TestHelperImpl implements TestHelper {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String key = "d6b36cb204310571665e1f549b0f8f75";
    private final String token = "ATTAe2690e32af06d2406d6e5fc36234cfec754fc05a17a6c1250f18e62c57bd5bc97800BEE9";

    // BOARDS
    public String getBoards() {
        return BoardsEndpoint.getBoards(restTemplate, key, token);
    }

    // LISTS
    public String postNewList(String name, String boardId) {
        return ListsEndpoint.postNewList(restTemplate, name, boardId, key, token);
    }

}
