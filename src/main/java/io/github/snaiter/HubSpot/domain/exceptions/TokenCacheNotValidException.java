package io.github.snaiter.HubSpot.domain.exceptions;

public class TokenCacheNotValidException extends RuntimeException {

    public TokenCacheNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenCacheNotValidException(String message) {
        super(message);
    }
}
