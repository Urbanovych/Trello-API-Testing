package trello.api.urbanovych.endpoints;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import trello.api.urbanovych.objects.Card;

import java.util.Collections;

import static trello.api.urbanovych.properties.PropertiesHelper.getBaseUrlProperties;
import static trello.api.urbanovych.properties.PropertiesHelper.getEndpointProperties;

public class CardEndpoint {

    private static String baseUrl = getBaseUrlProperties();
    private static final String endpointPath = getEndpointProperties("cardEndpoint");

    public static Card getCard(RestTemplate restTemplate, String cardId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(cardId)
                .queryParam("key", key)
                .queryParam("token", token);
        HttpEntity<Card> entity = new HttpEntity<>(headers);

        ResponseEntity<Card> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                });
        return result.getBody();
    }

    public static Card createCard(RestTemplate restTemplate, String listId, String key, String token, String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", String.valueOf(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/1/cards")
                .queryParam("idList", listId)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name);
        HttpEntity<Card> entity = new HttpEntity<>(headers);

        System.out.println(builder.toUriString());

        ResponseEntity<Card> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                Card.class);
        return result.getBody();
    }

    public static Card updateCard(RestTemplate restTemplate, String cardId, String key, String token, String updatedName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(cardId)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", updatedName);
        HttpEntity<Card> entity = new HttpEntity<>(headers);

        ResponseEntity<Card> result = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                entity,
                Card.class);
        return result.getBody();
    }

    public static HttpStatusCode deleteCard(RestTemplate restTemplate, String listId, String key, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .pathSegment(listId)
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
}
