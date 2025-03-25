package io.github.snaiter.HubSpot.infrastructure.gateway.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    @JsonProperty("properties")
    private Map<String, String> properties;

    public static ContactRequest from(String email, String firstName, String lastName, String phone, String company) {
        return new ContactRequest(Map.of(
                "email", email,
                "firstname", firstName,
                "lastname", lastName,
                "phone", phone,
                "company", company
        ));
    }
}