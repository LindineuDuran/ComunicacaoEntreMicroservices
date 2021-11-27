package br.com.llduran.ms.communication.bank.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.llduran.ms.communication.bank.api.model.PagamentoDTO;
import br.com.llduran.ms.communication.bank.api.model.RetornoDTO;
import br.com.llduran.ms.communication.bank.domain.service.PagamentoService;

@RestController
public class PagamentoController
{

	@Autowired
	private PagamentoService pagamentoService;

	@RequestMapping(path = "/pagamento", method = RequestMethod.POST)
	public ResponseEntity<RetornoDTO> pagamento(@Valid @NotNull @RequestBody PagamentoDTO pagamentoDTO)
	{

		pagamentoService.pagamento(pagamentoDTO);

		RetornoDTO retorno = new RetornoDTO();
		retorno.setMensagem("Pagamento registrado com sucesso");

		return new ResponseEntity<RetornoDTO>(retorno, HttpStatus.OK);
	}
}