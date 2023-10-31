package trello.api.urbanovych.Board;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import trello.api.urbanovych.TestHelperImpl;
import trello.api.urbanovych.objects.Board;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoardTest {

    TestHelperImpl testHelper = new TestHelperImpl();
    List<String> boardsToDeleting = new ArrayList<>();

    @AfterAll
    public void cleanup() {
        for (String boardId : boardsToDeleting) {
            testHelper.deleteBoard(boardId);
        }
    }

    @Test
    public void createBoardTest() {
        String name = "RestBoard-createBoardTest";

        Board createBoard = testHelper.createBoard(name);
        boardsToDeleting.add(createBoard.getId());

        Assertions.assertNotNull(createBoard.getId(), "BoardId should not equal to null");
        Assertions.assertEquals(name, createBoard.getName(), "Names are not equal");
    }

    @Test
    public void getBoardTest() {
        Board createBoard = testHelper.createBoard("RestBoard-getBoardTest");
        boardsToDeleting.add(createBoard.getId());

        Board getBoard = testHelper.getBoard(createBoard.getId());

        Assertions.assertEquals(createBoard.getId(), getBoard.getId(), "Ids are not equal");
        Assertions.assertEquals(createBoard.getName(), getBoard.getName(), "Names are not equal");
    }

    @Test
    public void updateBoardTitleTest() {
        String updatedName = "RestBoard-updatedBoardTest";

        Board createBoard = testHelper.createBoard("RestBoard-Board.BoardTest");
        boardsToDeleting.add(createBoard.getId());

        Board updateBoard = testHelper.updateBoardTitle(createBoard.getId(),updatedName);

        Assertions.assertEquals(createBoard.getId(), updateBoard.getId(), "Ids are not equal");
        Assertions.assertEquals(updatedName, updateBoard.getName(), "Name is not updated");
    }

    @Test
    public void deleteBoardTest() {
        Board createBoard = testHelper.createBoard("RestBoard-deleteBoardTest");
        HttpStatusCode deleteBoardStatusCode = testHelper.deleteBoard(createBoard.getId());

        Assertions.assertEquals(HttpStatus.OK, deleteBoardStatusCode, "Response code is not 200");
    }
}
