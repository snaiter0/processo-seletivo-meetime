package io.github.snaiter.HubSpot.ports.in;

import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;

public interface HuspotAutenticationPort {

    String gerarAuthUrl();

    String gerarTokenUseCase(String authorizationCode) throws Exception;

    TokenResponse recuperarToken();
}
