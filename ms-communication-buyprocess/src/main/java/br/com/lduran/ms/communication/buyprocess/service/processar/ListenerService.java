package br.com.lduran.ms.communication.buyprocess.service.processar;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lduran.ms.communication.buyprocess.model.CompraChaveDTO;
import br.com.lduran.ms.communication.buyprocess.model.CompraFinalizadaDTO;
import br.com.lduran.ms.communication.buyprocess.model.PagamentoRetorno;
import br.com.lduran.ms.communication.buyprocess.service.bank.BankService;

@Component
public class ListenerService
{

	@Autowired
	private BankService bank;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${fila.entrada}")
	private String nomeFilaRepublicar;

	@Value("${fila.finalizado}")
	private String nomeFilaFinalizado;

	@RabbitListener(queues = "${fila.entrada}")
	public void listener(Message message) throws JsonParseException, JsonMappingException, IOException
	{
		String json = new String(message.getBody(), "UTF-8");

		System.out.println("Mensagem recebida:" + json);

		ObjectMapper mapper = new ObjectMapper();
		CompraChaveDTO compraChaveDTO = mapper.readValue(json, CompraChaveDTO.class);

		PagamentoRetorno pg = bank.pagar(compraChaveDTO);

		CompraFinalizadaDTO compraFinalizadaDTO = new CompraFinalizadaDTO();
		compraFinalizadaDTO.setCompraChaveDTO(compraChaveDTO);
		compraFinalizadaDTO.setPagamentoOK(pg.isPagamentoOK());
		compraFinalizadaDTO.setMensagem(pg.getMensagem());

		String jsonFinalizado = mapper.writeValueAsString(compraFinalizadaDTO);

		rabbitTemplate.convertAndSend(nomeFilaFinalizado, jsonFinalizado);
	}
}