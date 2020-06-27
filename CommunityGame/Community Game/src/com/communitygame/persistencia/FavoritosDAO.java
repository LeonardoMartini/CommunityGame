package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.communitygame.model.Favoritos;
import com.mysql.jdbc.PreparedStatement;


public class FavoritosDAO {
	
	private ConexaoMysql conexaofavoritos;
	
	public FavoritosDAO() {
		this.conexaofavoritos = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
	
	public Favoritos salvar(Favoritos favoritos) {
		this.conexaofavoritos.abrirConexao();
		String sqlInsert = "INSERT INTO favoritos VALUES(null, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaofavoritos.getConexao().clientPrepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setLong(1, favoritos.getUsuarioFavorita().getIdUsuario());
			prepare.setLong(2, favoritos.getPostagemFavorita().getIdPostagem());
			prepare.executeUpdate();
			
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				favoritos.setIdFavorito(id);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaofavoritos.fecharConexao();
		}
		return favoritos;
	}
	public void excluir(long id) {
		this.conexaofavoritos.abrirConexao();
		String sqlDelete = "DELETE FROM favoritos WHERE id_favorito=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaofavoritos.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaofavoritos.fecharConexao();
		}
	}
	
	public ArrayList<Favoritos> buscarFavoritosPorIdUsuario(long IdUsuario) {
		this.conexaofavoritos.abrirConexao();
		ArrayList<Favoritos> listaFavoritosUsuario = new ArrayList<Favoritos>();
		Favoritos favoritos = null;
		String sqlBuscarFavoritosPorIdUsuario = "SELECT * FROM favoritos WHERE id_usuario=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaofavoritos.getConexao().prepareStatement(sqlBuscarFavoritosPorIdUsuario);
			prepare.setLong(1, IdUsuario);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				favoritos = new Favoritos();
				favoritos.setIdFavorito(rs.getLong("id_favorito"));
				listaFavoritosUsuario.add(favoritos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFavoritosUsuario;
	}
}
