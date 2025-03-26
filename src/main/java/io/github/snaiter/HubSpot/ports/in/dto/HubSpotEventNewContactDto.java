package io.github.snaiter.HubSpot.ports.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HubSpotEventNewContactDto {
    private Long vid;

    @JsonProperty("properties")
    private Properties properties;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        @JsonProperty("firstname")
        private PropertyValue nome;

        @JsonProperty("lastname")
        private PropertyValue sobrenome;

        @JsonProperty("email")
        private PropertyValue email;

        @JsonProperty("phone")
        private PropertyValue telefone;

        @JsonProperty("company")
        private PropertyValue company;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PropertyValue {
        private String value;
    }
}
