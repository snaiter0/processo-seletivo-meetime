package io.github.snaiter.HubSpot.ports.out.persistence;

import io.github.snaiter.HubSpot.domain.model.TokenCache;

import java.time.LocalDateTime;
import java.util.Set;

public interface TokenCachePortRepository {
    Set<TokenCache> findByData(LocalDateTime dataLimit);

    TokenCache save(TokenCache tokenCache);
}
