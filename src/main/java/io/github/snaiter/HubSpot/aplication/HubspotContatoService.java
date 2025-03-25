package io.github.snaiter.HubSpot.aplication;

import io.github.snaiter.HubSpot.aplication.usecases.CriarContatoUseCaseImpl;
import io.github.snaiter.HubSpot.aplication.usecases.ProcessarInsercaoContatoUseCaseImpl;
import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.ports.in.HubspotContatoPort;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubspotContatoService implements HubspotContatoPort {

    private final CriarContatoUseCaseImpl criarContatoUseCase;
    private final ProcessarInsercaoContatoUseCaseImpl processarInsercaoContatoUseCase;

    @Override
    public ContactResponse criarContato(ContactRequest contactRequest) {
        return criarContatoUseCase.criarContato(contactRequest);
    }

    @Override
    public ContatoDto processarContato(ContatoDto contatoDto) {
        return processarInsercaoContatoUseCase.persistirContato(contatoDto);
    }

}
