package br.com.llduran.ms.communication.bank.domain.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.llduran.ms.communication.bank.domain.entity.Cartao;
import br.com.llduran.ms.communication.bank.domain.repository.CartaoRepository;

@Service
public class CartaoService
{
	@Autowired
	private CartaoRepository cartaoRepository;

	public boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao)
	{
		return cartaoRepository.findCartaoValido(codigoSegurancaCartao, nroCartao) > 0;
	}

	public boolean isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra)
	{
		return cartaoRepository.isSaldoSuficiente(codigoSegurancaCartao, nroCartao, valorCompra) > 0;
	}

	public Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao)
	{
		return cartaoRepository.findCartao(codigoSegurancaCartao, nroCartao);
	}

	@Transactional
	public void atualizarSaldo(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra)
	{
		cartaoRepository.atualizarSaldo(codigoSegurancaCartao, nroCartao, valorCompra);
	}
}