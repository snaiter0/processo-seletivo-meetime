package io.github.snaiter.HubSpot.adapters.in;

import io.github.snaiter.HubSpot.aplication.HubspotAutenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hubspot/auth")
@RequiredArgsConstructor
@Slf4j
public class HubsportAutenticationController {

    private final HubspotAutenticationService hubspotAutenticationService;

    // 🔹 1️⃣ Redireciona o usuário para o login do HubSpot
    @GetMapping("/login")
    public Object redirectToAuth() {
        String uri = hubspotAutenticationService.gerarAuthUrl();
        log.info("Generated Auth URL: {}", uri);
        return uri;
    }

    // 🔹 2️⃣ Callback após autenticação para capturar o token
    @GetMapping("/callback")
    public String handleOAuthCallback(@RequestParam("code") String code) throws Exception {
        return "Access Token: " + hubspotAutenticationService.gerarTokenUseCase(code);
    }
}
