package io.github.snaiter.HubSpot.infrastructure.configuration;

import io.github.resilience4j.ratelimiter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RateLimiterRestTemplateConfig {

    @Bean
    public RateLimiter hubspotRateLimiter() {
        // Configura o rate limiter
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(100) // 100 requests por segundo
                .limitRefreshPeriod(Duration.ofSeconds(1)) // Refresh do limite a cada 1 segundo
                .timeoutDuration(Duration.ofMillis(500)) // Timeout se o limite for excedido
                .build();

        RateLimiterRegistry registry = RateLimiterRegistry.of(config);

        // Cria e retorna o RateLimiter
        return registry.rateLimiter("hubspotRateLimiter");
    }

    @Bean
    public RestTemplate restTemplate(RateLimiter rateLimiter) {
        // Configura o RestTemplate com rate limiting usando o RateLimiter
        RateLimiterHttpRequestInterceptor interceptor = new RateLimiterHttpRequestInterceptor(rateLimiter);
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory()) {
            {
                // Adiciona o interceptor que aplica o rate limit a todas as requisições
                getInterceptors().add(interceptor);
            }
        };
    }
}
