package br.com.lduran.ms.communication.buytrip.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoDTO
{
	private String mensagem;
	private String chavePesquisa;
}