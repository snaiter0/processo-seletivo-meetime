package io.github.snaiter.HubSpot.aplication.usecases;

import io.github.snaiter.HubSpot.domain.mapper.ContatoMapper;
import io.github.snaiter.HubSpot.ports.out.persistence.ContatoPortRepository;
import io.github.snaiter.HubSpot.ports.in.usecases.ProcessarInsercaoContatoUseCase;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessarInsercaoContatoUseCaseImpl implements ProcessarInsercaoContatoUseCase {

    private final ContatoMapper contatoMapper;
    private final ContatoPortRepository contatoPortRepository;

    public ContatoDto persistirContato(ContatoDto contact) {
        return contatoMapper.toContatoDto(contatoPortRepository.save(contatoMapper.toContato(contact)));
    }
}
