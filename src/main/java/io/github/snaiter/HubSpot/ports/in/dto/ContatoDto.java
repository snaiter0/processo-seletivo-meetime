package io.github.snaiter.HubSpot.ports.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContatoDto(
        Long id,
        @JsonProperty("vid") Long hubspotId,
        @JsonProperty("firstname") String firstname,
        @JsonProperty("lastname") String lastname,
        @JsonProperty("company") String company,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone,
        @JsonProperty("date_of_birth") LocalDateTime dateOfBirth,
        @JsonProperty("gender") String gender,
        @JsonProperty("address") String address,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("rg") String rg,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("registration_date") LocalDateTime registrationDate,
        @JsonProperty("status") String status,
        @JsonProperty("credit_limit") BigDecimal creditLimit,
        @JsonProperty("credit_usage") BigDecimal creditUsage,
        @JsonProperty("balance") BigDecimal balance,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("last_access_date") LocalDateTime lastAccessDate,
        @JsonProperty("active_plan") Boolean activePlan,
        @JsonProperty("access_level") String accessLevel,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("last_payment_date") LocalDateTime lastPaymentDate) {
}
