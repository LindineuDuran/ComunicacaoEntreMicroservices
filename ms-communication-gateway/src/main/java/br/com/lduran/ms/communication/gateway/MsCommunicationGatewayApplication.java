package br.com.lduran.ms.communication.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MsCommunicationGatewayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MsCommunicationGatewayApplication.class, args);
	}
}
