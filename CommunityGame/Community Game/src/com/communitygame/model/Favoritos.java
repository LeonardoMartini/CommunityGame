package com.communitygame.model;

public class Favoritos {
	
	private long idFavorito;
	private Usuario usuarioFavorita;
	private Postagem postagemFavorita;
	
	public Favoritos(long idFavorito, Usuario usuarioFavorita, Postagem postagemFavorita) {
		super();
		this.idFavorito = idFavorito;
		this.usuarioFavorita = usuarioFavorita;
		this.postagemFavorita = postagemFavorita;
	}

	public Favoritos() {
		super();
	}

	public long getIdFavorito() {
		return idFavorito;
	}

	public void setIdFavorito(long idFavorito) {
		this.idFavorito = idFavorito;
	}

	public Usuario getUsuarioFavorita() {
		return usuarioFavorita;
	}

	public void setUsuarioFavorita(Usuario usuarioFavorita) {
		this.usuarioFavorita = usuarioFavorita;
	}

	public Postagem getPostagemFavorita() {
		return postagemFavorita;
	}

	public void setPostagemFavorita(Postagem postagemFavorita) {
		this.postagemFavorita = postagemFavorita;
	}

	@Override
	public String toString() {
		return "Favoritos idFavorito=" + idFavorito + ", usuarioFavorita=" + usuarioFavorita + ", postagemFavorita="
				+ postagemFavorita;
	}
	
	

}
