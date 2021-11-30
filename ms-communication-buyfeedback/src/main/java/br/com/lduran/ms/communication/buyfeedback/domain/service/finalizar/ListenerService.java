package br.com.lduran.ms.communication.buyfeedback.domain.service.finalizar;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lduran.ms.communication.buyfeedback.api.model.CompraFinalizadaDTO;
import br.com.lduran.ms.communication.buyfeedback.domain.entity.CompraRedis;
import br.com.lduran.ms.communication.buyfeedback.domain.repository.CompraRedisRepository;

@Service
public class ListenerService
{
	@Autowired
	private CompraRedisRepository compraRedisRepository;

	@RabbitListener(queues = "${fila.finalizado}")
	public void listener(Message message) throws JsonParseException, JsonMappingException, IOException
	{
		String json = new String(message.getBody(), "UTF-8");

		System.out.println("Mensagem recebida:" + json);

		ObjectMapper mapper = new ObjectMapper();

		// Trata erro de mapeamento do json para CompraFinalizadaDTO
		JsonNode parsedNodes = mapper.readValue(json, JsonNode.class);

		CompraFinalizadaDTO compraFinalizadaDTO = mapper.readValue(parsedNodes.asText(), CompraFinalizadaDTO.class);

		CompraRedis credis = new CompraRedis();
		credis.setId(compraFinalizadaDTO.getCompraChaveDTO().getChave());
		credis.setMensagem(compraFinalizadaDTO.getMensagem());
		credis.setNroCartao(compraFinalizadaDTO.getCompraChaveDTO().getCompraDTO().getNroCartao());
		credis.setValorPassagem(compraFinalizadaDTO.getCompraChaveDTO().getCompraDTO().getValorPassagem());
		credis.setCodigoPassagem(compraFinalizadaDTO.getCompraChaveDTO().getCompraDTO().getCodigoPassagem());
		credis.setPagamentoOK(compraFinalizadaDTO.isPagamentoOK());

		System.out.println("Gravando no redis....");
		compraRedisRepository.save(credis);
	}
}
