package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Seguidores;
import com.mysql.jdbc.PreparedStatement;

public class SeguidoresDAO {
	
	private ConexaoMysql conexaoSeguidores;
	
	public SeguidoresDAO() {
		this.conexaoSeguidores = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
	
	public Seguidores salvar (Seguidores seguidores) {
		this.conexaoSeguidores.abrirConexao();
		String sqlInsert = "INSERT INTO seguidores VALUES(null, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoSeguidores.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setLong(1, seguidores.getUsuarioSegue().getIdUsuario());
			prepare.setLong(2, seguidores.getUsuarioSeguidor().getIdUsuario());
			prepare.executeUpdate();
			
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				seguidores.setIdSeguidores(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoSeguidores.fecharConexao();
		}
		return seguidores;
	}
	
	public Seguidores conta (Seguidores seguidores) {
		this.conexaoSeguidores.abrirConexao();
		String sqlSelect = "SELECT COUNT (*) FROM seguidores WHERE id_seguindo=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoSeguidores.getConexao().prepareStatement(sqlSelect, Statement.RETURN_GENERATED_KEYS);
			prepare.setLong(1, seguidores.getUsuarioSegue().getIdUsuario());
			prepare.executeUpdate();
			
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				seguidores.setIdSeguidores(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoSeguidores.fecharConexao();
		}
		return seguidores;
	}
	
	public void excluir(long id) {
		this.conexaoSeguidores.abrirConexao();
		String sqlDelete = "DELETE FROM seguidores WHERE id_seguir=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoSeguidores.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoSeguidores.fecharConexao();
		}
	}
	
	public List<Seguidores> buscarTodos() {
		this.conexaoSeguidores.abrirConexao();
		List<Seguidores> listaSeguidores = new ArrayList<Seguidores>();
		Seguidores seguidores = null;
		String sqlBuscarTodos = "SELECT * FROM seguidores";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoSeguidores.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				seguidores = new Seguidores();
				seguidores.setIdSeguidores(rs.getLong("id_seguir"));
				listaSeguidores.add(seguidores);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSeguidores;
	}
	
	public Seguidores buscarPorId(long id) {
		this.conexaoSeguidores.abrirConexao();
		Seguidores seguidores = null;
		String sqlBuscarPorId = "SELECT * FROM seguidores WHERE id_seguir=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoSeguidores.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				seguidores = new Seguidores();
				seguidores.setIdSeguidores(rs.getLong("id_seguir"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seguidores;
	}
	
}
