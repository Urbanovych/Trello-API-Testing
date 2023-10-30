package trello.api.urbanovych;

import org.springframework.http.HttpStatusCode;
import trello.api.urbanovych.objects.Board;

public interface TestHelper {

    // BOARDS
    Board getBoard(String boardId);

    Board createBoard(String name);

    Board updateBoard(String boardId, String updatedName);

    HttpStatusCode deleteBoard(String boardId);

    // LISTS
    String postNewList(String name, String boardId);
}
