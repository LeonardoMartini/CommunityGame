package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Avaliacao;
import com.communitygame.model.Usuario;
import com.mysql.jdbc.PreparedStatement;

public class AvaliacaoDAO {
	
	private ConexaoMysql conexaoAvaliacao;
	
	public AvaliacaoDAO() {
		this.conexaoAvaliacao = new ConexaoMysql("localhost", "3306", "root", "200718", "communitygame");
	}
	
	public Avaliacao salvar (Avaliacao avaliacao) {
		this.conexaoAvaliacao.abrirConexao();
		String sqlInsert = "INSERT INTO avaliacao VALUES(null, ?, ?, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoAvaliacao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setDouble(1, avaliacao.getNota());
			prepare.setString(2, avaliacao.getComentario());
			prepare.setLong(3, avaliacao.getUsuarioAvaliado().getIdUsuario());
			prepare.setLong(4, avaliacao.getUsuarioAvaliador().getIdUsuario());
			prepare.executeUpdate();
			
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				avaliacao.setIdAvaliacao(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoAvaliacao.fecharConexao();
		}
		return avaliacao;
	}
	
	public void editar(Avaliacao avaliacao) {
		this.conexaoAvaliacao.abrirConexao();
		String sqlUpdate = "UPDATE avaliacao SET nota=?, comentario=?, id_avaliado=?, id_avaliador=? WHERE id_avaliacao=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoAvaliacao.getConexao().prepareStatement(sqlUpdate);
			prepare.setDouble(1, avaliacao.getNota());
			prepare.setString(2, avaliacao.getComentario());
			prepare.setLong(3, avaliacao.getUsuarioAvaliado().getIdUsuario());
			prepare.setLong(4, avaliacao.getUsuarioAvaliador().getIdUsuario());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoAvaliacao.fecharConexao();
		}
	}
	
	public void excluir(long id) {
		this.conexaoAvaliacao.abrirConexao();
		String sqlDelete = "DELETE FROM avaliacao WHERE id_avaliacao=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoAvaliacao.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoAvaliacao.fecharConexao();
		}
	}

	public List<Usuario> buscarTodos() {
		this.conexaoAvaliacao.abrirConexao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		String sqlBuscarTodos = "SELECT * FROM usuario";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoAvaliacao.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setApelido(rs.getString("apelido"));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	public Usuario buscarPorId(long id) {
		this.conexaoAvaliacao.abrirConexao();
		Usuario usuario = null;
		String sqlBuscarPorId = "SELECT * FROM usuario WHERE id_usuario=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoAvaliacao.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setApelido(rs.getString("apelido"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
