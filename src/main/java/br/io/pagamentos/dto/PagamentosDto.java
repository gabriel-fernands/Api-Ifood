package br.io.pagamentos.dto;

import br.io.pagamentos.enus.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PagamentosDto {

        private Long id;
        private BigDecimal valor;
        private String nome;
        private String numero;
        private String expiracao;
        private String codigo;
        private Status status;
        private Long formaDePagamentoId;
        private Long pedidoId;

    }
