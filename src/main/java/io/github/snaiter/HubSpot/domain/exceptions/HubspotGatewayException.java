package io.github.snaiter.HubSpot.domain.exceptions;

public class HubspotGatewayException extends RuntimeException {

    public HubspotGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public HubspotGatewayException(String message) {
        super(message);
    }
}
