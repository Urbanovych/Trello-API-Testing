package trello.api.urbanovych;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;
import trello.api.urbanovych.objects.Board;
import trello.api.urbanovych.objects.BoardList;

import java.util.List;

public interface TestHelper {

    // BOARDS
    Board getBoard(String boardId);

    Board createBoard(String name);

    Board updateBoardTitle(String boardId, String updatedName);

    HttpStatusCode deleteBoard(String boardId);

    List<BoardList> getListsOnBoard(String boardId);

    // LISTS
    BoardList getList(String listId);

    BoardList createList(String name, String boardId);

    BoardList updateListTitle(String listId, String updatedName);
}
