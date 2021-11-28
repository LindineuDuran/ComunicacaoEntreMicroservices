package br.com.lduran.ms.communication.buyprocess.service.bank;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lduran.ms.communication.buyprocess.model.BankRetornoDTO;
import br.com.lduran.ms.communication.buyprocess.model.CompraChaveDTO;
import br.com.lduran.ms.communication.buyprocess.model.PagamentoDTO;
import br.com.lduran.ms.communication.buyprocess.model.PagamentoRetorno;

@Service
public class BankService
{
	@Value("${bank.link}")
	private String link;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public PagamentoRetorno pagar(CompraChaveDTO compraChaveDTO) throws IOException
	{

		PagamentoDTO json = new PagamentoDTO();
		json.setNroCartao(compraChaveDTO.getCompraDTO().getNroCartao());
		json.setCodigoSegurancaCartao(compraChaveDTO.getCompraDTO().getCodigoSegurancaCartao());
		json.setValorCompra(compraChaveDTO.getCompraDTO().getValorPassagem());

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PagamentoDTO> entity = new HttpEntity<PagamentoDTO>(json, headers);

		try
		{
			ResponseEntity<BankRetornoDTO> bankRetorno = restTemplate.exchange(link, HttpMethod.POST, entity,
					BankRetornoDTO.class);
			return new PagamentoRetorno(bankRetorno.getBody().getMensagem(), true);
		}
		catch (HttpClientErrorException ex)
		{
			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST)
			{
				ObjectMapper mapper = new ObjectMapper();
				BankRetornoDTO obj = mapper.readValue(ex.getResponseBodyAsString(), BankRetornoDTO.class);
				return new PagamentoRetorno(obj.getMensagem(), false);
			}
			throw ex;
		}
		catch (RuntimeException ex)
		{
			throw ex;
		}
	}
}
