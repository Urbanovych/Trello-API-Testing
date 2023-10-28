import org.junit.jupiter.api.Test;
import trello.api.urbanovych.TestHelperImpl;

public class DummyTest {

    TestHelperImpl testHelper = new TestHelperImpl();

    @Test
    public void myDummyTest() {
        System.out.println(testHelper.getBoards());
    }

}
