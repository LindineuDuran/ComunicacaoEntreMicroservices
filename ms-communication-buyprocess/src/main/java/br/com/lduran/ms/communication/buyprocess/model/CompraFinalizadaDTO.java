package br.com.lduran.ms.communication.buyprocess.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraFinalizadaDTO
{

	private CompraChaveDTO compraChaveDTO;
	private String mensagem;
	private boolean pagamentoOK;
}