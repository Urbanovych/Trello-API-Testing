package trello.api.urbanovych.endpoints;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import trello.api.urbanovych.objects.Board;
import trello.api.urbanovych.objects.BoardList;

import java.util.Collections;
import java.util.List;

import static trello.api.urbanovych.properties.PropertiesHelper.getBaseUrlProperties;
import static trello.api.urbanovych.properties.PropertiesHelper.getEndpointProperties;

public class BoardsEndpoint {

    private static final String baseUrl = getBaseUrlProperties();
    private static final String endpointPath = getEndpointProperties("boardEndpoint");

    public static Board getBoard(RestTemplate restTemplate, String boardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(boardId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<Board> entity = new HttpEntity<>(headers);

        ResponseEntity<Board> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }

    public static Board createBoard(RestTemplate restTemplate, String name, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .queryParam("name", name)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<Board> entity = new HttpEntity<>(headers);

        ResponseEntity<Board> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                Board.class);
        return result.getBody();
    }

    public static Board updateBoard(RestTemplate restTemplate, String boardId, String key, String token, String updatedName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(boardId)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", updatedName);
        HttpEntity<Board> entity = new HttpEntity<>(headers);

        ResponseEntity<Board> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                entity,
                Board.class);
        return result.getBody();
    }

    public static HttpStatusCode deleteBoard(RestTemplate restTemplate, String boardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(boardId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<?> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.DELETE,
                entity,
                String.class);
        return result.getStatusCode();
    }

    public static List<BoardList> getListsOnBoard(RestTemplate restTemplate, String boardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(boardId)
                .pathSegment("lists")
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<List<BoardList>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<BoardList>> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }
}
