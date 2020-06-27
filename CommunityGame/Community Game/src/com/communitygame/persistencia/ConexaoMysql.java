package com.communitygame.persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoMysql {
	
	private String ip;
	private String porta;
	private String login;
	private String senha;
	private String nomeBd;
	private Connection conexao;
	
	public ConexaoMysql(String ip, String porta, String login, String senha, String nomeBd) {
		super();
		this.ip = ip;
		this.porta = porta;
		this.login = login;
		this.senha = senha;
		this.nomeBd = nomeBd;
	}

	public ConexaoMysql() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeBd() {
		return nomeBd;
	}

	public void setNomeBd(String nomeBd) {
		this.nomeBd = nomeBd;
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void abrirConexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conexao = (Connection) DriverManager.getConnection("jdbc:mysql://"+this.ip+":"+this.porta+"/"+this.nomeBd,this.login, this.senha);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void fecharConexao() {
		try {
				if(!conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
