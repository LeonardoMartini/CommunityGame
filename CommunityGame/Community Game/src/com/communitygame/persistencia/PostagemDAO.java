package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Postagem;
import com.mysql.jdbc.PreparedStatement;

public class PostagemDAO {
	
	private ConexaoMysql conexaoPostagem;
	private UsuarioDAO usuarioDAO;
	
	public PostagemDAO() {
		this.conexaoPostagem = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
	
	public Postagem salvar (Postagem postagem) {
		this.conexaoPostagem.abrirConexao(); 
		String sqlInsert = "INSERT INTO postagem VALUES(null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, postagem.getDescricaoPostagem());
			prepare.setString(2, postagem.getDataPostagem());
			prepare.setDouble(3, postagem.getValor());
			prepare.setString(4, postagem.getTitulo());
			prepare.setLong(5, postagem.getProdutoPostagem().getIdProduto());
			prepare.setLong(6, postagem.getPostagemCriada().getIdUsuario());
			prepare.executeUpdate();
			
			ResultSet rs = prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				postagem.setIdPostagem(id);;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoPostagem.fecharConexao();
		}
		return postagem;
	}
	
	public void editar(Postagem postagem) {
		this.conexaoPostagem.abrirConexao();
		String sqlUpdate = "UPDATE postagem SET descricao_postagem=?, data_postagem=?, valor=?, titulo=?, id_produto=?, id_criada=? WHERE id_postagem=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlUpdate);
			prepare.setString(1, postagem.getDescricaoPostagem());
			prepare.setString(2, postagem.getDataPostagem());
			prepare.setDouble(3, postagem.getValor());
			prepare.setString(4, postagem.getTitulo());
			prepare.setLong(5, postagem.getProdutoPostagem().getIdProduto());
			prepare.setLong(6, postagem.getPostagemCriada().getIdUsuario());
			prepare.setLong(7, postagem.getIdPostagem());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoPostagem.fecharConexao();
		}
	}
	
	public void excluir(long id) {
		this.conexaoPostagem.abrirConexao();
		String sqlDelete = "DELETE FROM postagem WHERE id_postagem=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoPostagem.fecharConexao();
		}
	}
	
	public List<Postagem> buscarTodos() {
		this.conexaoPostagem.abrirConexao();
		List<Postagem> listaPostagem = new ArrayList<Postagem>();
		Postagem postagem = null;
		String sqlBuscarTodos = "SELECT * FROM postagem";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				postagem= new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setTitulo(rs.getString("titulo"));
				listaPostagem.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPostagem;
	}
	
	public Postagem buscarPorTitulo(String titulo) {
		this.conexaoPostagem.abrirConexao();
		Postagem postagem = null;
		usuarioDAO = new UsuarioDAO();
		String sqlBuscarPorTitulo = "SELECT * FROM postagem WHERE titulo=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlBuscarPorTitulo);
			prepare.setString(1, titulo);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				postagem= new Postagem();
				postagem.setDescricaoPostagem(rs.getString("descricao_postagem"));
				postagem.setTitulo(rs.getString("titulo"));
				postagem.setPostagemCriada(usuarioDAO.buscarPorApelido("apelido"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postagem;
	}
	
	public Postagem buscarPorId(long id) {
		this.conexaoPostagem.abrirConexao();
		Postagem postagem = null;
		String sqlBuscarPorId = "SELECT * FROM postagem WHERE id_postagem=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				postagem= new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setTitulo(rs.getString("titulo"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postagem;
	}
	public ArrayList<Postagem> buscarPostagemPorIdUsuario(long IdUsuario) {
		this.conexaoPostagem.abrirConexao();
		ArrayList<Postagem> listaPostagemUsuario = new ArrayList<Postagem>();
		Postagem postagem = null;
		String sqlBuscarPostagemPorIdUsuario = "SELECT * FROM postagem WHERE id_criada=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoPostagem.getConexao().prepareStatement(sqlBuscarPostagemPorIdUsuario);
			prepare.setLong(1, IdUsuario);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setTitulo(rs.getString("titulo"));
				postagem.setDescricaoPostagem(rs.getString("descricao_postagem"));
				postagem.setDataPostagem(rs.getString("data_postagem"));
				postagem.setValor(rs.getDouble("valor"));
				listaPostagemUsuario.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPostagemUsuario;
	}
}
