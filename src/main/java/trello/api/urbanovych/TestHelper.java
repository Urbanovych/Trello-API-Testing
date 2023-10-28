package trello.api.urbanovych;

public interface TestHelper {

    // BOARDS
    String getBoards();

    // LISTS
    String postNewList(String name, String boardId);
}
