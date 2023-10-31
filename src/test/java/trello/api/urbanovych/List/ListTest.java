package trello.api.urbanovych.List;

import org.junit.jupiter.api.*;
import org.springframework.util.Assert;
import trello.api.urbanovych.TestHelperImpl;
import trello.api.urbanovych.objects.BoardList;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListTest {

    TestHelperImpl testHelper = new TestHelperImpl();
    private List<BoardList> listsInBoard;
    private String boardId;

    @BeforeAll
    public void createBoard() {
        boardId = testHelper.createBoard("RestBoard-ListTests").getId();
        listsInBoard = testHelper.getListsOnBoard(boardId);
    }

    @AfterAll
    public void cleanup() {
        testHelper.deleteBoard(boardId);
    }

    @Test
    public void createListTest() {
        String name = "RestList";
        BoardList boardList = testHelper.createList(name, boardId);

        Assertions.assertNotNull(boardList.getId(), "ListId should not equal to null");
        Assertions.assertEquals(name, boardList.getName(), "Name is not updated. List is not created");
    }

    @Test
    public void getListTest() {
        BoardList ToDoBoardList = listsInBoard.stream()
                .filter(f -> f.getName().equals("To Do"))
                .findFirst().get();

        BoardList getBoardList = testHelper.getList(ToDoBoardList.getId());

        Assertions.assertEquals(ToDoBoardList.getId(), getBoardList.getId(), "ListIds are not equal");
        Assertions.assertEquals(ToDoBoardList.getName(), getBoardList.getName(), "Names are not equal");
    }

    @Test
    public void updateListTest() {
        String updatedName = "NotDone";
        BoardList DoneBoardList = listsInBoard.stream()
                .filter(f -> f.getName().equals("Done"))
                .findFirst().get();

        BoardList updatedBoardList = testHelper.updateListTitle(DoneBoardList.getId(), updatedName);

        Assertions.assertEquals(DoneBoardList.getId(), updatedBoardList.getId(), "ListIds are not equal");
        Assertions.assertEquals(updatedName, updatedBoardList.getName(), "Name is not updated");
    }
}
