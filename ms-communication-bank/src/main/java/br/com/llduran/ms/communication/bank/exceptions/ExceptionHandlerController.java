package br.com.llduran.ms.communication.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.llduran.ms.communication.bank.api.model.RetornoDTO;

@ControllerAdvice
public class ExceptionHandlerController
{

	@ExceptionHandler(PagamentoException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RetornoDTO process(RuntimeException ex)
	{
		return new RetornoDTO(ex.getMessage());
	}

}