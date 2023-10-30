package trello.api.urbanovych.endpoints;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static trello.api.urbanovych.properties.PropertiesHelper.getBaseUrlProperties;

public class ListsEndpoint {

    private static String baseUrl = getBaseUrlProperties();

    public static String postNewList(RestTemplate restTemplate, String name, String boardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/lists")
                .queryParam("name", name)
                .queryParam("boardId", boardId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<?> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);
        return result.toString();
    }
}
