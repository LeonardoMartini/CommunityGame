package com.communitygame.model;

public class Produto {
	
	private long idProduto;
	private String tipoProduto;
	private String descricao;
	private Usuario usuario;
	
	public Produto(long idProduto, String tipoProduto, String descricao, Usuario usuario) {
		super();
		this.idProduto = idProduto;
		this.tipoProduto = tipoProduto;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	public Produto() {
		super();
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Produto idProduto=" + idProduto + ", tipoProduto=" + tipoProduto + ", descricao=" + descricao
				+ ", usuario=" + usuario;
	}
	
	
	
}
