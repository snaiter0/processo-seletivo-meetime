package io.github.snaiter.HubSpot.ports.in.usecases;

import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;

public interface CriarContatoUseCase {
    ContactResponse criarContato(ContactRequest contactRequest);
}
