package br.com.lduran.ms.communication.buyprocess.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO
{
	private Integer nroCartao;
	private Integer codigoSegurancaCartao;
	private BigDecimal valorCompra;
}