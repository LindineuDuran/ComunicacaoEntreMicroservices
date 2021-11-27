package br.com.lduran.ms.communication.buytrip.api.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lduran.ms.communication.buytrip.api.model.CompraChaveDTO;
import br.com.lduran.ms.communication.buytrip.api.model.CompraDTO;
import br.com.lduran.ms.communication.buytrip.api.model.RetornoDTO;

@RestController
public class CompraController
{
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${fila.saida}")
	private String nomeFila;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<RetornoDTO> pagamento(@Valid @NotNull @RequestBody CompraDTO compraDTO) throws Exception
	{

		CompraChaveDTO compraChaveDTO = new CompraChaveDTO();
		compraChaveDTO.setCompraDTO(compraDTO);
		compraChaveDTO.setChave(UUID.randomUUID().toString());

		ObjectMapper obj = new ObjectMapper();

		String json = obj.writeValueAsString(compraChaveDTO);

		rabbitTemplate.convertAndSend(nomeFila, json);

		RetornoDTO retorno = new RetornoDTO();
		retorno.setMensagem("Compra registrada com sucesso. Aguarda a confirmação do pagamento.");
		retorno.setChavePesquisa(compraChaveDTO.getChave());

		return new ResponseEntity<RetornoDTO>(retorno, HttpStatus.OK);
	}
}