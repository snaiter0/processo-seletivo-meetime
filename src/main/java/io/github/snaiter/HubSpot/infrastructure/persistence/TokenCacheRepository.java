package io.github.snaiter.HubSpot.infrastructure.persistence;

import io.github.snaiter.HubSpot.domain.model.TokenCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

public interface TokenCacheRepository extends JpaRepository<TokenCache, Long> {

    @Query(value = """
            SELECT tc FROM TokenCache tc 
                        where tc.expirationTime > :dataLimit 
                                    order by tc.expirationTime desc
            """)
    Set<TokenCache> findByData(@Param("dataLimit") LocalDateTime dataLimit);
}
