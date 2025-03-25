package io.github.snaiter.HubSpot.ports.in;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;

public interface HuspotAutenticationPort {

    String gerarAuthUrl();

    TokenCache gerarTokenUseCase(String authorizationCode) throws Exception;

    TokenResponse recuperarToken();
}
