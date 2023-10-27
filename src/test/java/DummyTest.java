import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class DummyTest {

    @Test
    public void myDummyTest() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://api.trello.com/1/members/me/boards?key=d6b36cb204310571665e1f549b0f8f75&token=ATTAe2690e32af06d2406d6e5fc36234cfec754fc05a17a6c1250f18e62c57bd5bc97800BEE9";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }

}
