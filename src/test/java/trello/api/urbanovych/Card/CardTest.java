package trello.api.urbanovych.Card;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import trello.api.urbanovych.TestHelperImpl;
import trello.api.urbanovych.objects.BoardList;
import trello.api.urbanovych.objects.Card;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CardTest {

    TestHelperImpl testHelper = new TestHelperImpl();
    private BoardList boardList;
    private String boardId;

    @BeforeAll
    public void createBoard() {
        boardId = testHelper.createBoard("RestBoard-CardTests").getId();
        boardList = testHelper.getListsOnBoard(boardId).get(0);
    }

    @AfterAll
    public void cleanup() {
        testHelper.deleteBoard(boardId);
    }

    @Test
    public void createCardTest() {
        String name = "RestCard-CreateTest";
        Card card = testHelper.createCard(boardList.getId(), name);

        Assertions.assertNotNull(card.getId(), "ListId should not equal to null");
        Assertions.assertEquals(name, card.getName(), "Name is not equal to expected. List is not created");
    }

    @Test
    public void getCardTest() {
        String name = "RestCard-GetTest";
        Card card = testHelper.createCard(boardList.getId(), name);

        Card getCard = testHelper.getCard(card.getId());

        Assertions.assertEquals(card.getId(), getCard.getId(), "ListIds are not equal");
        Assertions.assertEquals(card.getName(), getCard.getName(), "Names are not equal");
    }

    @Test
    public void updateCardTest() {
        String updatedName = "RestCard-UpdatedTest";
        Card card = testHelper.createCard(boardList.getId(), "RestCard-UpdateTest");

        Card updatedCard = testHelper.updateCardTitle(card.getId(), updatedName);

        Assertions.assertEquals(card.getId(), updatedCard.getId(), "ListIds are not equal");
        Assertions.assertEquals(updatedName, updatedCard.getName(), "Name is not updated");
    }

    @Test
    public void deleteCardTest() {
        String name = "RestCard-DeleteTest";
        Card card = testHelper.createCard(boardList.getId(), name);

        HttpStatusCode deleteCardStatusCode = testHelper.deleteCard(card.getId());

        Assertions.assertEquals(HttpStatus.OK, deleteCardStatusCode, "Response code is not 200");
    }
}
