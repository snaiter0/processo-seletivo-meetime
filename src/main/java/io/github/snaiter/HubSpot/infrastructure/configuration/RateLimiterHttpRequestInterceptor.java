package io.github.snaiter.HubSpot.infrastructure.configuration;

import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;  // Corrigido para ClientHttpResponse

import java.io.IOException;

public class RateLimiterHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final RateLimiter rateLimiter;

    public RateLimiterHttpRequestInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Tenta adquirir uma permissão do rate limiter antes de executar a requisição
        rateLimiter.acquirePermission();  // Aqui ele tenta adquirir uma permissão antes de seguir com a requisição
        return execution.execute(request, body);
    }
}
