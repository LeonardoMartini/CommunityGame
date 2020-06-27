package com.communitygame.model;

public class Mensagem {
	
	private long idMensagem;
	private String texto;
	private Usuario mensagemEnviada;
	private Usuario mensagemRecebida;
	
	public Mensagem(long idMensagem, String texto, Usuario mensagemEnviada, Usuario mensagemRecebida) {
		super();
		this.idMensagem = idMensagem;
		this.texto = texto;
		this.mensagemEnviada = mensagemEnviada;
		this.mensagemRecebida = mensagemRecebida;
	}
	
	public Mensagem() {
		super();
	}
	public long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Usuario getMensagemEnviada() {
		return mensagemEnviada;
	}
	public void setMensagemEnviada(Usuario mensagemEnviada) {
		this.mensagemEnviada = mensagemEnviada;
	}
	public Usuario getMensagemRecebida() {
		return mensagemRecebida;
	}
	public void setMensagemRecebida(Usuario mensagemRecebida) {
		this.mensagemRecebida = mensagemRecebida;
	}

	@Override
	public String toString() {
		return "Mensagem idMensagem=" + idMensagem + ", texto=" + texto + ", mensagemEnviada=" + mensagemEnviada
				+ ", mensagemRecebida=" + mensagemRecebida;
	}
	
	

}
