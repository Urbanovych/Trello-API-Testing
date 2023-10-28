package trello.api.urbanovych.endpoints;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class BoardsEndpoint {

    private static String uri = "https://api.trello.com/1/members/me/boards";

    public static String getBoards(RestTemplate restTemplate, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(uri)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<?> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
        return result.toString();
    }
}
