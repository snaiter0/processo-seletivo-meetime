package io.github.snaiter.HubSpot.ports.in.usecases;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import org.springframework.cache.annotation.Cacheable;

public interface GerarTokenUseCase {
    @Cacheable(value = "hubspotToken", condition = "!(#result!=null && result.expirationTime.isBefore(T(java.time.LocalDateTime).now()))")
    TokenResponse getToken(String authorizationCode) throws Exception;

    TokenResponse recuperarToken();

    TokenCache gerarToken(String authorizationCode) throws Exception;
}
