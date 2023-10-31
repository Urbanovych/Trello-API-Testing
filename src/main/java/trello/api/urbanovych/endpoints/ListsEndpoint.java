package trello.api.urbanovych.endpoints;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import trello.api.urbanovych.objects.BoardList;

import static trello.api.urbanovych.properties.PropertiesHelper.getBaseUrlProperties;
import static trello.api.urbanovych.properties.PropertiesHelper.getEndpointProperties;

public class ListsEndpoint {

    private static String baseUrl = getBaseUrlProperties();
    private static final String endpointPath = getEndpointProperties("listEndpoint");

    public static BoardList getList(RestTemplate restTemplate, String listId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(listId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<BoardList> entity = new HttpEntity<>(headers);

        ResponseEntity<BoardList> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }

    public static BoardList createList(RestTemplate restTemplate, String name, String boardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/1/lists")
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<BoardList> entity = new HttpEntity<>(headers);

        ResponseEntity<BoardList> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }

    public static BoardList updateList(RestTemplate restTemplate, String listId, String name, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(listId)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name);
        HttpEntity<BoardList> entity = new HttpEntity<>(headers);

        ResponseEntity<BoardList> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }
}
