package com.communitygame.model;

import java.util.ArrayList;

public class Usuario {
	
	private long idUsuario;
	private String nome;
	private String apelido;
	private String email;
	private String estado;
	private String cidade;
	private String senha;
	private ArrayList <Produto> listaProduto;
	private ArrayList <Postagem> listaPostagem;
	private ArrayList <Favoritos> listaFavoritos;
	
	public Usuario(long idUsuario, String nome, String apelido, String email, String estado,
			String cidade, String senha, ArrayList<Produto> listaProduto, ArrayList<Postagem> listaPostagem,
			ArrayList<Favoritos> listaFavoritos) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.estado = estado;
		this.cidade = cidade;
		this.senha = senha;
		this.listaProduto = listaProduto;
		this.listaPostagem = listaPostagem;
		this.listaFavoritos = listaFavoritos;
	}

	public Usuario() {
		super();
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(ArrayList<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public ArrayList<Postagem> getListaPostagem() {
		return listaPostagem;
	}

	public void setListaPostagem(ArrayList<Postagem> listaPostagem) {
		this.listaPostagem = listaPostagem;
	}

	public ArrayList<Favoritos> getListaFavoritos() {
		return listaFavoritos;
	}

	public void setListaFavoritos(ArrayList<Favoritos> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}

	@Override
	public String toString() {
		return "Usuario idUsuario=" + idUsuario + ", nome=" + nome + ", apelido=" + apelido + ", email=" + email
				+ ", estado=" + estado + ", cidade=" + cidade + ", senha=" + senha + ", listaProduto=" + listaProduto
				+ ", listaPostagem=" + listaPostagem + ", listaFavoritos=" + listaFavoritos;
	}

	
	
}
