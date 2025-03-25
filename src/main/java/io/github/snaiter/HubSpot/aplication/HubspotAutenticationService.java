package io.github.snaiter.HubSpot.aplication;

import io.github.snaiter.HubSpot.aplication.usecases.GerarAuthorizationUrlUseCaseImpl;
import io.github.snaiter.HubSpot.aplication.usecases.GerarTokenUseCaseImpl;
import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import io.github.snaiter.HubSpot.ports.in.HuspotAutenticationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubspotAutenticationService implements HuspotAutenticationPort {

    private final GerarAuthorizationUrlUseCaseImpl gerarAuthorizationUrlUseCase;
    private final GerarTokenUseCaseImpl gerarTokenUseCase;

    @Override
    public String gerarAuthUrl(){
        log.info("gerando url");
        return gerarAuthorizationUrlUseCase.gerarAuthorizationUrl();
    }

    @Override
    public TokenCache gerarTokenUseCase(String authorizationCode) throws Exception {
        return gerarTokenUseCase.gerarToken(authorizationCode);
    }

    @Override
    public TokenResponse recuperarToken(){
        return gerarTokenUseCase.recuperarToken();
    }
}
