package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Mensagem;
import com.mysql.jdbc.PreparedStatement;

public class MensagemDAO {
		
	private ConexaoMysql conexaoMensagem;
	
	public MensagemDAO() {
		this.conexaoMensagem = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
	
	public Mensagem salvar (Mensagem mensagem) {
		this.conexaoMensagem.abrirConexao();
		String sqlInsert = "INSERT INTO mensagem VALUES(null, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoMensagem.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, mensagem.getTexto());
			prepare.executeUpdate();
			
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				mensagem.setIdMensagem(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoMensagem.fecharConexao();
		}
		return mensagem;
	}
	
	public void editar(Mensagem mensagem) {
		this.conexaoMensagem.abrirConexao();
		String sqlUpdate = "UPDATE mensagem SET texto=?, mensagem_enviada=? WHERE id_mensagem=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoMensagem.getConexao().prepareStatement(sqlUpdate);
			prepare.setString(1, mensagem.getTexto());
			prepare.setLong(2, mensagem.getMensagemEnviada().getIdUsuario());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoMensagem.fecharConexao();
		}
	}
	
	public void excluir(long id) {
		this.conexaoMensagem.abrirConexao();
		String sqlDelete = "DELETE FROM mensagem WHERE id_mensagem=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoMensagem.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoMensagem.fecharConexao();
		}
	}
	
	public List<Mensagem> buscarTodos() {
		this.conexaoMensagem.abrirConexao();
		List<Mensagem> listaMensagem = new ArrayList<Mensagem>();
		Mensagem mensagem = null;
		String sqlBuscarTodos = "SELECT * FROM mensagem";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoMensagem.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				mensagem = new Mensagem();
				mensagem.setIdMensagem(rs.getLong("id_mensagem"));
				mensagem.setTexto(rs.getString("texto"));
				listaMensagem.add(mensagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMensagem;
	}
	
	
	
	public Mensagem buscarPorId(long id) {
		this.conexaoMensagem.abrirConexao();
		Mensagem mensagem = null;
		String sqlBuscarPorId = "SELECT * FROM mensagem WHERE id_mensagem=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoMensagem.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				mensagem = new Mensagem();
				mensagem.setIdMensagem(rs.getLong("id_mensagem"));
				mensagem.setTexto(rs.getString("texto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensagem;
	}
}
