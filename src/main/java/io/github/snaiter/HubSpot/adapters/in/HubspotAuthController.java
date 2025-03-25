package io.github.snaiter.HubSpot.adapters.in;

import io.github.snaiter.HubSpot.aplication.HubspotService;
import io.github.snaiter.HubSpot.infrastructure.gateway.request.ContactRequest;
import io.github.snaiter.HubSpot.infrastructure.gateway.response.ContactResponse;
import io.github.snaiter.HubSpot.ports.in.dto.ContatoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hubspot")
@RequiredArgsConstructor
@Slf4j
public class HubspotAuthController {

    private final HubspotService hubspotService;

    // 🔹 1️⃣ Redireciona o usuário para o login do HubSpot
    @GetMapping("/oauth/login")
    public Object redirectToAuth() {
        String uri = hubspotService.getAuthUrl();
        log.info("Generated Auth URL: {}", uri);
        return uri;
    }

    // 🔹 2️⃣ Callback após autenticação para capturar o token
    @GetMapping("/callback")
    public String handleOAuthCallback(@RequestParam("code") String code) throws Exception {
        return "Access Token: " + hubspotService.getToken(code);
    }

    @PostMapping(value = "/new-contact", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResponse> criarContato(@RequestBody ContactRequest contactRequest) {
        return new ResponseEntity<>(hubspotService.criarContato(contactRequest), HttpStatus.OK) ;
    }

    @PostMapping(value = "/webhook/contact-creation")
        public ResponseEntity<ContatoDto> criarContatoWebhook(@RequestBody ContatoDto contato) {
        log.info("Recebido evento 'contact.creation' do hubspot. " +
                "Payload: {}", contato);
        return ResponseEntity.ok(hubspotService.persistirContato(contato));
        }
}
