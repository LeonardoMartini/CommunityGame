package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Produto;
import com.communitygame.model.Usuario;
import com.mysql.jdbc.PreparedStatement;

public class ProdutoDAO {
	
	private ConexaoMysql conexaoProduto;
	
	public ProdutoDAO() {
		this.conexaoProduto = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
	
	public Produto salvar (Produto produto) {
		this.conexaoProduto.abrirConexao();
		String sqlInsert = "INSERT INTO produto VALUES(null, ?, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, produto.getTipoProduto());
			prepare.setString(2, produto.getDescricao());
			prepare.setLong(3, produto.getUsuario().getIdUsuario());
			prepare.executeUpdate();
			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				produto.setIdProduto(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoProduto.fecharConexao();
		}
		return produto;
	}
	
	public void editar(Produto produto) {
		this.conexaoProduto.abrirConexao();
		String sqlUpdate = "UPDATE produto SET tipo_produto=?, descricao_produto=?, id_usuario=? WHERE id_produto=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlUpdate);
			prepare.setString(1, produto.getTipoProduto());
			prepare.setString(2, produto.getDescricao());
			prepare.setLong(3, produto.getUsuario().getIdUsuario());
			prepare.setLong(4, produto.getIdProduto());

			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoProduto.fecharConexao();
		}
	}
	
	public void excluir(long id) {
		this.conexaoProduto.abrirConexao();
		String sqlDelete = "DELETE FROM produto WHERE id_produto=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoProduto.fecharConexao();
		}
	}
	
	public List<Produto> buscarTodos() {
		this.conexaoProduto.abrirConexao();
		List<Produto> listaProduto = new ArrayList<Produto>();
		Produto produto = null;
		String sqlBuscarTodos = "SELECT * FROM produto";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				produto = new Produto();
				produto.setIdProduto(rs.getLong("id_produto"));
				produto.setTipoProduto(rs.getString("tipo_produto"));
				produto.setDescricao(rs.getString("descricao_produto"));
				listaProduto.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProduto;
	}
	
	public Produto buscarPorId(long id) {
		this.conexaoProduto.abrirConexao();
		Produto produto = null;
		String sqlBuscarPorId = "SELECT * FROM produto WHERE id_produto=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				produto = new Produto();
				produto.setIdProduto(rs.getLong("id_produto"));
				produto.setTipoProduto(rs.getString("tipo_produto"));
				produto.setDescricao(rs.getString("descricao_produto"));
				
				UsuarioDAO uDAO = new UsuarioDAO();
		     	Usuario u = uDAO.buscarPorId(rs.getLong("id_usuario"));
				produto.setUsuario(u);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}
	public ArrayList<Produto> buscarProdutosPorIdUsuario(long IdUsuario) {
	this.conexaoProduto.abrirConexao();
	ArrayList<Produto> listaProdutoUsuario = new ArrayList<Produto>();
	Produto produto = null;
	String sqlBuscarProdutosPorIdUsuario = "SELECT * FROM produto WHERE id_usuario=?";
	try {
		PreparedStatement prepare = (PreparedStatement) this.conexaoProduto.getConexao().prepareStatement(sqlBuscarProdutosPorIdUsuario);
		prepare.setLong(1, IdUsuario);
		
		ResultSet rs = prepare.executeQuery();
		
		while(rs.next()) {
			produto = new Produto();
			produto.setIdProduto(rs.getLong("id_produto"));
			produto.setTipoProduto(rs.getString("tipo_produto"));
			produto.setDescricao(rs.getString("descricao_produto"));
			listaProdutoUsuario.add(produto);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return listaProdutoUsuario;
}
}
