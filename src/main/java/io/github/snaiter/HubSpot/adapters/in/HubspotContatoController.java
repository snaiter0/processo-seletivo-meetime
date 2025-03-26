package io.github.snaiter.HubSpot.adapters.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.ports.in.HubspotContatoPort;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import io.github.snaiter.HubSpot.ports.in.dto.HubSpotEventNewContactDto;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hubspot/contato")
@RequiredArgsConstructor
@Slf4j
public class HubspotContatoController {

    private final HubspotContatoPort hubspotContatoPort;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResponse> criarContato(@RequestBody ContactRequest contactRequest) {
        return new ResponseEntity<>(hubspotContatoPort.criarContato(contactRequest), HttpStatus.OK) ;
    }

    @PostMapping(value = "/webhook/contact-creation")
    public ResponseEntity<ContatoDto> criarContatoWebhook(@RequestBody HubSpotEventNewContactDto payload) {
        log.info("Recebido evento 'contact.creation' do hubspot. ");

        return ResponseEntity.ok(hubspotContatoPort.processarContato(payload));
    }
}
