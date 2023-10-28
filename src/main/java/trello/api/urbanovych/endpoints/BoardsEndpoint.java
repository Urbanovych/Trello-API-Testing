package trello.api.urbanovych.endpoints;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static trello.api.urbanovych.properties.PropertiesHelper.getUrlProperties;

public class BoardsEndpoint {

    private static String baseUrl = getUrlProperties();

    public static String getBoards(RestTemplate restTemplate, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/members/me/boards")
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
