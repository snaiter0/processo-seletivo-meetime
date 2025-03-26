package io.github.snaiter.HubSpot.ports.out.gateway;

import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;

public interface HubspotGatewayPort {
    // 🔹 Gera a URL de Autorização para o Usuário se autenticar
    String getAuthUrl();

    TokenResponse getAccessToken(String authorizationCode);

    ContactResponse createContact(ContactRequest contactRequest, TokenResponse token);
}
