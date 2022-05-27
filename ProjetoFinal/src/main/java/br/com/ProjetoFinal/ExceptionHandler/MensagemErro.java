package br.com.ProjetoFinal.ExceptionHandler;

public class MensagemErro {

	private int statusCode;
	private String mensagem;

	public MensagemErro(int statusCode, String mensagem) {
		this.statusCode = statusCode;
		this.mensagem = mensagem;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMensagem() {
		return mensagem;
	}

}