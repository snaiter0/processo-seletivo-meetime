package io.github.snaiter.HubSpot.adapters.out.persistence;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.persistence.TokenCacheRepository;
import io.github.snaiter.HubSpot.ports.out.persistence.TokenCachePortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class TokenCacheAdapterRepository implements TokenCachePortRepository {

    private final TokenCacheRepository tokenCacheRepository;

    @Override
    public Set<TokenCache> findByData(LocalDateTime dataLimit){
        return tokenCacheRepository.findByData(dataLimit);
    }

    @Override
    public TokenCache save(TokenCache tokenCache) {
        return tokenCacheRepository.save(tokenCache);
    }
}
