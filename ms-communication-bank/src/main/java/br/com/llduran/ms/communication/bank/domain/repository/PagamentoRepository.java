package br.com.llduran.ms.communication.bank.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.llduran.ms.communication.bank.domain.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>
{

}
