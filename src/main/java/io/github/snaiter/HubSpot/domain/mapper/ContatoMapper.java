package io.github.snaiter.HubSpot.domain.mapper;

import io.github.snaiter.HubSpot.domain.model.Contato;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import io.github.snaiter.HubSpot.ports.in.dto.HubSpotEventNewContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContatoMapper {

    @Mapping(source = "eventNewContactDto.vid", target = "hubspotId")
    @Mapping(source = "eventNewContactDto.properties.nome.value", target = "nome")
    @Mapping(source = "eventNewContactDto.properties.email.value", target = "email")
    @Mapping(source = "eventNewContactDto.properties.company.value", target = "empresa")
    @Mapping(source = "eventNewContactDto.properties.sobrenome.value", target = "sobrenome")
    @Mapping(source = "eventNewContactDto.properties.telefone.value", target = "telefone")
    Contato toContato(HubSpotEventNewContactDto eventNewContactDto);

    ContatoDto toContatoDto(Contato contato);
}
