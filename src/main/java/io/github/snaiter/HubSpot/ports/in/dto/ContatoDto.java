package io.github.snaiter.HubSpot.ports.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContatoDto(
                            Long idHubspot,
                            String nome,
                            String sobrenome,
                            String email,
                            String telefone,
                            LocalDateTime dataNascimento,
                            String sexo,
                            String endereco,
                            String cpf,
                            String rg,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                            LocalDateTime dataRegistro,
                            String status,
                            BigDecimal limiteCredito,
                            BigDecimal valorUsoCredito,
                            BigDecimal saldo,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                            LocalDateTime dataUltimoAcesso,
                            Boolean planoAtivo,
                            String nivelAcesso,
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                            LocalDateTime dataUltimoPagamento) {
}
