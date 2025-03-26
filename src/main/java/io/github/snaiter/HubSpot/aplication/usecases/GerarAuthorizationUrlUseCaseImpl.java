package io.github.snaiter.HubSpot.aplication.usecases;

import io.github.snaiter.HubSpot.ports.in.usecases.GerarAuthorizationUrlUseCase;
import io.github.snaiter.HubSpot.ports.out.gateway.HubspotGatewayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GerarAuthorizationUrlUseCaseImpl implements GerarAuthorizationUrlUseCase {

    private final HubspotGatewayPort hubspotGatewayPort;

    public String gerarAuthorizationUrl() {
        return hubspotGatewayPort.getAuthUrl();
    }

}
