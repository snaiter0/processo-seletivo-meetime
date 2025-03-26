package io.github.snaiter.HubSpot.ports.out.persistence;

import io.github.snaiter.HubSpot.domain.model.Contato;

public interface ContatoPortRepository {
    Contato save(Contato contato);
}
