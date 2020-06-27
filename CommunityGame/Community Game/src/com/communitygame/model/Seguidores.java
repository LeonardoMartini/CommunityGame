package com.communitygame.model;

public class Seguidores {
	
	private long idSeguidores;
	private Usuario usuarioSegue;
	private Usuario usuarioSeguidor;
	
	public Seguidores(long idSeguidores, Usuario usuarioSegue, Usuario usuarioSeguidor) {
		super();
		this.idSeguidores = idSeguidores;
		this.usuarioSegue = usuarioSegue;
		this.usuarioSeguidor = usuarioSeguidor;
	}

	public Seguidores() {
		super();
	}

	public long getIdSeguidores() {
		return idSeguidores;
	}

	public void setIdSeguidores(long idSeguidores) {
		this.idSeguidores = idSeguidores;
	}

	public Usuario getUsuarioSegue() {
		return usuarioSegue;
	}

	public void setUsuarioSegue(Usuario usuarioSegue) {
		this.usuarioSegue = usuarioSegue;
	}

	public Usuario getUsuarioSeguidor() {
		return usuarioSeguidor;
	}

	public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
		this.usuarioSeguidor = usuarioSeguidor;
	}

	@Override
	public String toString() {
		return "Seguidores idSeguidores=" + idSeguidores + ", usuarioSegue=" + usuarioSegue + ", usuarioSeguidor="
				+ usuarioSeguidor;
	}
	
	

}
