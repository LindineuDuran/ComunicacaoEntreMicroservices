package br.com.lduran.ms.communication.buyfeedback.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.lduran.ms.communication.buyfeedback.domain.entity.CompraRedis;

@Repository
public interface CompraRedisRepository extends CrudRepository<CompraRedis, String>
{

}
