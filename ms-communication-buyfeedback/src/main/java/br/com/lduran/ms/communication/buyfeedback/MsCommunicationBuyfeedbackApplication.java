package br.com.lduran.ms.communication.buyfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class MsCommunicationBuyfeedbackApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MsCommunicationBuyfeedbackApplication.class, args);
	}
}
