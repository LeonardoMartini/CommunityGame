package com.communitygame.model;

public class Avaliacao {
	
	private long idAvaliacao;
	private double nota;
	private String comentario;
	private Usuario usuarioAvaliado;
	private Usuario usuarioAvaliador;
	
	public Avaliacao(long idAvaliacao, double notaUsuario, String comentarioUsuario, Usuario usuarioAvaliado,
			Usuario usuarioAvaliador) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.nota = notaUsuario;
		this.comentario = comentarioUsuario;
		this.usuarioAvaliado = usuarioAvaliado;
		this.usuarioAvaliador = usuarioAvaliador;
	}
	public Avaliacao() {
		super();
	}
	public long getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Usuario getUsuarioAvaliado() {
		return usuarioAvaliado;
	}
	public void setUsuarioAvaliado(Usuario usuarioAvaliado) {
		this.usuarioAvaliado = usuarioAvaliado;
	}
	public Usuario getUsuarioAvaliador() {
		return usuarioAvaliador;
	}
	public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
		this.usuarioAvaliador = usuarioAvaliador;
	}
	@Override
	public String toString() {
		return "Avaliacao idAvaliacao=" + idAvaliacao + ", nota=" + nota + ", comentario=" + comentario
				+ ", usuarioAvaliado=" + usuarioAvaliado + ", usuarioAvaliador=" + usuarioAvaliador + "]";
	}
	
	

}
