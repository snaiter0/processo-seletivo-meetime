package io.github.snaiter.HubSpot.ports.in;

import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;

public interface HubspotContatoPort {
    ContactResponse criarContato(ContactRequest contactRequest);

    ContatoDto processarContato(ContatoDto contatoDto);
}
