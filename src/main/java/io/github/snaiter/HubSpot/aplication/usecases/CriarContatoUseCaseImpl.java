package io.github.snaiter.HubSpot.aplication.usecases;

import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.ports.in.HuspotAutenticationPort;
import io.github.snaiter.HubSpot.ports.in.usecases.CriarContatoUseCase;
import io.github.snaiter.HubSpot.ports.out.gateway.HubspotGatewayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CriarContatoUseCaseImpl implements CriarContatoUseCase {

    private final HuspotAutenticationPort huspotAutenticationPort;
    private final HubspotGatewayPort hubspotGatewayPort;

    /**
     * Registra um novo contato no hubspot
     * @param contactRequest Dados do novo contato
     * @return {@link ContactResponse} Response da requisicao HTTP.
     */
    @Override
    public ContactResponse criarContato(ContactRequest contactRequest) {
        return hubspotGatewayPort.createContact(contactRequest, huspotAutenticationPort.recuperarToken());
    }
}
