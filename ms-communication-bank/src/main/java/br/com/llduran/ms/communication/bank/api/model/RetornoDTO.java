package br.com.llduran.ms.communication.bank.api.model;

public class RetornoDTO
{
	private String mensagem;

	public RetornoDTO()
	{
	}

	public RetornoDTO(String message)
	{
		mensagem = message;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}
}
