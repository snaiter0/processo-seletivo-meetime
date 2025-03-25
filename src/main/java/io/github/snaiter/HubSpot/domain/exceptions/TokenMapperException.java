package io.github.snaiter.HubSpot.domain.exceptions;

public class TokenMapperException extends RuntimeException {

    public TokenMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenMapperException(String message) {
        super(message);
    }

}
