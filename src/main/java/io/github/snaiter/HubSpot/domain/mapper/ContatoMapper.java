package io.github.snaiter.HubSpot.domain.mapper;

import io.github.snaiter.HubSpot.domain.model.Contato;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContatoMapper {

    Contato toContato(ContatoDto contato);

    ContatoDto toContatoDto(Contato contato);
}
