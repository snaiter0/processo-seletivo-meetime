package io.github.snaiter.HubSpot.domain.exceptions;

public class TokenCacheNotValidException extends RuntimeException {

    public TokenCacheNotValidException(String message, Throwable cause, String tokenRequest) {
        super(message.concat("\n Gere um novo token: "+tokenRequest), cause);
    }

    public TokenCacheNotValidException(String message, String tokenRequest) {
        super(message.concat("\n Gere um novo token: "+tokenRequest));
    }
}
