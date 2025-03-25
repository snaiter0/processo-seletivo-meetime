package io.github.snaiter.HubSpot.infrastructure.gateway;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
@RequiredArgsConstructor
public class HubspotGateway {

    private final RestTemplate restTemplate;

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.auth-url}")
    private String authUrl;

    @Value("${hubspot.token-url}")
    private String tokenUrl;

    @Value("${hubspot.redirect-uri.callback}")
    private String redirectUri;

    @Value("${hubspot.optional-scopes}")
    private String optionalScopes;

    @Value("${hubspot.scope}")
    private String scope;


    // 🔹 Gera a URL de Autorização para o Usuário se autenticar
    public String getAuthUrl() {
        return UriComponentsBuilder.fromHttpUrl(authUrl)
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .queryParam("optional_scope", optionalScopes)
                .toUriString();
    }

    public TokenResponse getAccessToken(String authorizationCode) {
        log.info("Gerando novo token de acesso...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, TokenResponse.class);

        if (response.getBody() != null) {
            response.getBody().updateExpirationTime();
        }

        log.info("HubSpot Token Response: {}", response.getBody());
        return response.getBody();
    }

    public ContactResponse createContact(ContactRequest contactRequest, TokenResponse token) {
        String url = "https://api.hubapi.com/crm/v3/objects/contacts";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getAccessToken()); // Método que obtém o token válido

        HttpEntity<ContactRequest> request = new HttpEntity<>(contactRequest, headers);
        ResponseEntity<ContactResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, ContactResponse.class);

        return response.getBody();
    }

}
