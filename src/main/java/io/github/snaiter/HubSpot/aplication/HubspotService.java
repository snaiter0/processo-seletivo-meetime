package io.github.snaiter.HubSpot.aplication;

import io.github.snaiter.HubSpot.domain.mapper.ContatoMapper;
import io.github.snaiter.HubSpot.domain.model.Contato;
import io.github.snaiter.HubSpot.domain.model.TokenCache;
import io.github.snaiter.HubSpot.domain.exceptions.TokenCacheNotValidException;
import io.github.snaiter.HubSpot.domain.mapper.TokenMapper;
import io.github.snaiter.HubSpot.infrastructure.gateway.HubspotGateway;
import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.TokenResponse;
import io.github.snaiter.HubSpot.infrastructure.persistence.ContatoRepository;
import io.github.snaiter.HubSpot.infrastructure.persistence.TokenCacheRepository;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubspotService {

    private final HubspotGateway hubspotGateway;
    private final TokenCacheRepository tokenCacheRepository;
    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;
    private final TokenMapper tokenMapper;

    public String getAuthUrl(){
        log.info("gerando url");
        return hubspotGateway.getAuthUrl();
    }

    @Cacheable(value = "hubspotToken", condition = "!(#result!=null && result.expirationTime.isBefore(T(java.time.LocalDateTime).now()))")
    public TokenResponse getToken(String authorizationCode) throws Exception {
        log.info("Recuperando token");
        return tokenMapper.toTokenResponse(tokenCacheRepository.findByData(LocalDateTime.now()).stream().findFirst()
                .orElse(gerarToken(authorizationCode)));
    }

    public ContactResponse criarContato(ContactRequest contactRequest) {
       return hubspotGateway.createContact(contactRequest, recuperarToken());
    }

    public ContatoDto persistirContato(ContatoDto contact) {
        return contatoMapper.toContatoDto(contatoRepository.save(contatoMapper.toContato(contact)));
    }

    private TokenCache gerarToken(String authorizationCode) throws Exception {
        log.info("gerando novo token");
        return tokenCacheRepository.save(tokenMapper.toTokenCache(hubspotGateway.getAccessToken(authorizationCode)));
    }

    private TokenResponse recuperarToken(){
        log.info("recuperando token");
        try {
            return tokenMapper.toTokenResponse(tokenCacheRepository.findByData(
                    LocalDateTime.now()).stream().findFirst().orElseThrow(() -> new TokenCacheNotValidException("O token esta invalido. ", getAuthUrl())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
