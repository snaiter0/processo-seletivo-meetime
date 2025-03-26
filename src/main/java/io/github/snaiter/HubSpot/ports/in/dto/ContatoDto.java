package io.github.snaiter.HubSpot.ports.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContatoDto(
        Long id,
        @JsonProperty("id_hubspot") Long hubspotId,
        @JsonProperty("nome") String nome,
        @JsonProperty("sobrenome") String sobrenome,
        @JsonProperty("empresa") String empresa,
        @JsonProperty("email") String email,
        @JsonProperty("telefone") String telefone,
        @JsonProperty("data_nascimento") LocalDateTime dataNascimento,
        @JsonProperty("sexo") String sexo,
        @JsonProperty("endereco") String endereco,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("rg") String rg,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("data_registro") LocalDateTime dataRegistro,
        @JsonProperty("status") String status,
        @JsonProperty("limite_credito") BigDecimal limiteCredito,
        @JsonProperty("valor_uso_credito") BigDecimal valorUsoCredito,
        @JsonProperty("saldo") BigDecimal saldo,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("data_ultimo_acesso") LocalDateTime dataUltimoAcesso,
        @JsonProperty("plano_ativo") Boolean planoAtivo,
        @JsonProperty("nivel_acesso") String nivelAcesso,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonProperty("data_ultimo_pagamento") LocalDateTime dataUltimoPagamento) {
}
