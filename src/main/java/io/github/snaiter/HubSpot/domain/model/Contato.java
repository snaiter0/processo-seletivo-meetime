package io.github.snaiter.HubSpot.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "contato", schema = "contato")
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_hubspot")
    private Long hubspotId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "status")
    private String status;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;

    @Column(name = "valor_uso_credito")
    private BigDecimal valorUsoCredito;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_ultimo_acesso")
    private LocalDateTime dataUltimoAcesso;

    @Column(name = "plano_ativo")
    private Boolean planoAtivo;

    @Column(name = "nivel_acesso")
    private String nivelAcesso;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_ultimo_pagamento")
    private LocalDateTime dataUltimoPagamento;

}
