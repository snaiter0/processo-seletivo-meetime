package io.github.snaiter.HubSpot.infrastructure.gateway.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;

public class ContactResponse {

    @JsonProperty("properties")
    private Map<String, String> properties;

    @JsonProperty("createdAt")
    private Instant createdAt;

    @JsonProperty("updatedAt")
    private Instant updatedAt;

    @JsonProperty("archived")
    private boolean archived;
}
