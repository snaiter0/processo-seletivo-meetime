package io.github.snaiter.HubSpot.adapters.out.persistence;

import io.github.snaiter.HubSpot.domain.model.Contato;
import io.github.snaiter.HubSpot.infrastructure.persistence.ContatoRepository;
import io.github.snaiter.HubSpot.ports.out.persistence.ContatoPortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContatoAdapterRepository implements ContatoPortRepository {

    private final ContatoRepository contatoRepository;

    @Override
    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }
}
