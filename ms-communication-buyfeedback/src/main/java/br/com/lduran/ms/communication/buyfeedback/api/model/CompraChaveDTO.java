package br.com.lduran.ms.communication.buyfeedback.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompraChaveDTO
{
	private String chave;
	private CompraDTO compraDTO;
}