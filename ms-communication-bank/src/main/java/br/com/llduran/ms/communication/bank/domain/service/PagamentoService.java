package br.com.llduran.ms.communication.bank.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.llduran.ms.communication.bank.api.model.PagamentoDTO;
import br.com.llduran.ms.communication.bank.domain.entity.Pagamento;
import br.com.llduran.ms.communication.bank.domain.repository.PagamentoRepository;
import br.com.llduran.ms.communication.bank.exceptions.PagamentoException;

@Service
public class PagamentoService
{

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private CartaoService cartaoService;

	@Transactional
	public void pagamento(PagamentoDTO pagamentoDTO)
	{
		if (!cartaoService.isValido(pagamentoDTO.getCodigoSegurancaCartao(), pagamentoDTO.getNroCartao()))
		{
			throw new PagamentoException("Cartão inválido.");
		}

		if (!cartaoService.isSaldoSuficiente(pagamentoDTO.getCodigoSegurancaCartao(), pagamentoDTO.getNroCartao(),
				pagamentoDTO.getValorCompra()))
		{
			throw new PagamentoException("Cartão não possui saldo suficiente.");
		}

		Pagamento pagamento = new Pagamento();
		pagamento.setValorCompra(pagamentoDTO.getValorCompra());
		pagamento.setCartao(
				cartaoService.getCartao(pagamentoDTO.getCodigoSegurancaCartao(), pagamentoDTO.getNroCartao()));

		pagamentoRepository.save(pagamento);

		cartaoService.atualizarSaldo(pagamentoDTO.getCodigoSegurancaCartao(), pagamentoDTO.getNroCartao(),
				pagamentoDTO.getValorCompra());
	}
}