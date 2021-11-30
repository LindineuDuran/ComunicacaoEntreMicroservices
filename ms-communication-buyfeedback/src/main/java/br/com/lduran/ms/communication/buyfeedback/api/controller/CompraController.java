package br.com.lduran.ms.communication.buyfeedback.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lduran.ms.communication.buyfeedback.domain.entity.CompraRedis;
import br.com.lduran.ms.communication.buyfeedback.domain.repository.CompraRedisRepository;
import br.com.lduran.ms.communication.buyfeedback.exceptions.NaoFinalizadoException;

@RestController
public class CompraController
{
	@Autowired
	private CompraRedisRepository compraRedisRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<CompraRedis> listar()
	{
		List<CompraRedis> compras = (List<CompraRedis>) compraRedisRepository.findAll();

		return compras;
	}

	@RequestMapping(path = "/{chave}", method = RequestMethod.GET)
	public CompraRedis status(@PathVariable("chave") String chave)
	{
		Optional<CompraRedis> compra = compraRedisRepository.findById(chave);

		if (!compra.isPresent())
		{
			throw new NaoFinalizadoException();
		}

		return compra.get();
	}

	@RequestMapping(path = "/{chave}", method = RequestMethod.DELETE)
	public void excluir(@PathVariable("chave") String chave)
	{
		compraRedisRepository.deleteById(chave);
	}

	@RequestMapping(path = "/meunome", method = RequestMethod.GET)
	public String status()
	{
		return "Estou na máquina do: LLDURAN";
	}
}