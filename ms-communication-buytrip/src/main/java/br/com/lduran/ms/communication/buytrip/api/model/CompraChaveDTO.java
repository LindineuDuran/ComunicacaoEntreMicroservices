package br.com.lduran.ms.communication.buytrip.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraChaveDTO
{
	private String chave;
	private CompraDTO compraDTO;
}