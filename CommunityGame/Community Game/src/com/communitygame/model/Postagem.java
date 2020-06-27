package com.communitygame.model;

public class Postagem {
	
	private long idPostagem;
	private String descricaoPostagem;
	private String dataPostagem;
	private double valor;
	private String titulo;
	private Produto produtoPostagem;
	private Usuario postagemCriada;
	
	public Postagem(long idPostagem, String descricaoPostagem, String dataPostagem, double valorProduto, String titulo,
			Produto produtoPostagem, Usuario postagemCriada) {
		super();
		this.idPostagem = idPostagem;
		this.descricaoPostagem = descricaoPostagem;
		this.dataPostagem = dataPostagem;
		this.valor = valorProduto;
		this.titulo = titulo;
		this.produtoPostagem = produtoPostagem;
		this.postagemCriada = postagemCriada;
	}

	public Postagem() {
		super();
	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getDescricaoPostagem() {
		return descricaoPostagem;
	}

	public void setDescricaoPostagem(String descricaoPostagem) {
		this.descricaoPostagem = descricaoPostagem;
	}

	public String getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Produto getProdutoPostagem() {
		return produtoPostagem;
	}

	public void setProdutoPostagem(Produto produtoPostagem) {
		this.produtoPostagem = produtoPostagem;
	}

	public Usuario getPostagemCriada() {
		return postagemCriada;
	}

	public void setPostagemCriada(Usuario postagemCriada) {
		this.postagemCriada = postagemCriada;
	}

	@Override
	public String toString() {
		return "Postagem idPostagem=" + idPostagem + ", descricaoPostagem=" + descricaoPostagem + ", dataPostagem="
				+ dataPostagem + ", valor=" + valor + ", titulo=" + titulo + ", produtoPostagem=" + produtoPostagem
				+ ", postagemCriada=" + postagemCriada;
	}
	
	

}
