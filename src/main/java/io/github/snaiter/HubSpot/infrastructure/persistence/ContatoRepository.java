package io.github.snaiter.HubSpot.infrastructure.persistence;

import io.github.snaiter.HubSpot.domain.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
