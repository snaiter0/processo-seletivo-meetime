package io.github.snaiter.HubSpot.aplication.usecases;

import io.github.snaiter.HubSpot.domain.exceptions.TokenCacheNotValidException;
import io.github.snaiter.HubSpot.domain.exceptions.TokenMapperException;
import io.github.snaiter.HubSpot.domain.mapper.TokenMapper;
import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import io.github.snaiter.HubSpot.ports.in.usecases.GerarTokenUseCase;
import io.github.snaiter.HubSpot.ports.out.persistence.TokenCachePortRepository;
import io.github.snaiter.HubSpot.ports.out.gateway.HubspotGatewayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GerarTokenUseCaseImpl implements GerarTokenUseCase {

    private final HubspotGatewayPort hubspotGatewayPort;
    private final TokenMapper tokenMapper;
    private final TokenCachePortRepository tokenCachePortRepository;

    @Cacheable(value = "hubspotToken", condition = "!(#result!=null && result.expirationTime.isBefore(T(java.time.LocalDateTime).now()))")
    @Override
    public TokenResponse getToken(String authorizationCode) throws Exception {
        log.info("Recuperando token");
        return tokenMapper.toTokenResponse(tokenCachePortRepository.findByData(LocalDateTime.now()).stream().findFirst()
                .orElse(gerarToken(authorizationCode)));
    }

    @Override
    public TokenResponse recuperarToken(){
        log.info("recuperando token");
        try {
            return tokenMapper.toTokenResponse(tokenCachePortRepository.findByData(LocalDateTime.now()).stream().findFirst().orElseThrow(()
                    -> new TokenCacheNotValidException("Nao existem tokens validos, faca login novamente.")));
        } catch (Exception e) {
            throw new TokenMapperException("Falha ao mapear o token:  ", e);
        }
    }

    @Override
    public TokenCache gerarToken(String authorizationCode) throws Exception {
        log.info("gerando novo token");
        return tokenCachePortRepository.save(tokenMapper.toTokenCache(hubspotGatewayPort.getAccessToken(authorizationCode)));
    }
}
