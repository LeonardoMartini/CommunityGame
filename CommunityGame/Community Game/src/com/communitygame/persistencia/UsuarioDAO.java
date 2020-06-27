
package com.communitygame.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.communitygame.model.Seguidores;
import com.communitygame.model.Usuario;
import com.mysql.jdbc.PreparedStatement;

public class UsuarioDAO {
	
	private ConexaoMysql conexaoUsuario;
	private ProdutoDAO produtoDAO;
	private PostagemDAO postagemDAO;
	private FavoritosDAO favoritosDAO;
	
	public UsuarioDAO() {
		this.conexaoUsuario = new ConexaoMysql("localhost", "3306", "root", "ifsul2017", "communitygame");
	}
		
	public Usuario salvar(Usuario usuario) {
		this.conexaoUsuario.abrirConexao();
		String sqlInsert = "INSERT INTO usuario VALUES(null,?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, usuario.getNome());
			prepare.setString(2, usuario.getApelido());
			prepare.setString(3, usuario.getSenha());
			prepare.setString(4, usuario.getEmail());
			prepare.setString(5, usuario.getEstado());
			prepare.setString(6, usuario.getCidade());
			prepare.executeUpdate();

			ResultSet rs =prepare.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				usuario.setIdUsuario(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexaoUsuario.fecharConexao();
		}
		return usuario;
	}
	
	public void editar(Usuario usuario) {
		this.conexaoUsuario.abrirConexao();
		String sqlUpdate = "UPDATE usuario SET nome=?, apelido=?, senha=?, email=?, estado=?, cidade=? WHERE id_usuario=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlUpdate);
			prepare.setString(1, usuario.getNome());
			prepare.setString(2, usuario.getApelido());
			prepare.setString(3, usuario.getSenha());
			prepare.setString(4, usuario.getEmail());
			prepare.setString(5, usuario.getEstado());
			prepare.setString(6, usuario.getCidade());
			prepare.setLong(7,usuario.getIdUsuario());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoUsuario.fecharConexao();
		}
	}
	
	public void excluir(long id) {
		this.conexaoUsuario.abrirConexao();
		String sqlDelete = "DELETE FROM usuario WHERE id_usuario=?";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlDelete);
			prepare.setLong(1, id);
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.conexaoUsuario.fecharConexao();
		}
	}
	
	public List<Usuario> buscarTodos() {
		this.conexaoUsuario.abrirConexao();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		produtoDAO = new ProdutoDAO();
		postagemDAO = new PostagemDAO();
		favoritosDAO = new FavoritosDAO();
		String sqlBuscarTodos = "SELECT * FROM usuario";
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlBuscarTodos);
			
			ResultSet rs = prepare.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setListaProduto(produtoDAO.buscarProdutosPorIdUsuario(usuario.getIdUsuario()));
				usuario.setListaPostagem(postagemDAO.buscarPostagemPorIdUsuario(usuario.getIdUsuario()));
				usuario.setListaFavoritos(favoritosDAO.buscarFavoritosPorIdUsuario(usuario.getIdUsuario()));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	public Usuario buscarPorId(long id) {
		this.conexaoUsuario.abrirConexao();
		Usuario usuario = null;
		String sqlBuscarPorId = "SELECT * FROM usuario WHERE id_usuario=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setLong(1, id);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
		
	public Usuario buscarPorApelido(String apelido) {
		this.conexaoUsuario.abrirConexao();
		Usuario usuario = null;
		produtoDAO = new ProdutoDAO();
		postagemDAO = new PostagemDAO();
		favoritosDAO = new FavoritosDAO();
		String sqlBuscarPorApelido = "SELECT * FROM usuario WHERE apelido=?";
				
				try {
					PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlBuscarPorApelido);
					prepare.setString(1, apelido);
					
					ResultSet rs = prepare.executeQuery();
					
					if(rs.next()) {
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setNome(rs.getString("nome"));
						usuario.setApelido(rs.getString("apelido"));
						usuario.setEmail(rs.getString("email"));
						usuario.setEstado(rs.getString("estado"));
						usuario.setCidade(rs.getString("cidade"));
						usuario.setListaProduto(produtoDAO.buscarProdutosPorIdUsuario(usuario.getIdUsuario()));
						usuario.setListaPostagem(postagemDAO.buscarPostagemPorIdUsuario(usuario.getIdUsuario()));
						usuario.setListaFavoritos(favoritosDAO.buscarFavoritosPorIdUsuario(usuario.getIdUsuario()));

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return usuario;
	}
	
	public Usuario buscarPorCidade(String cidade) {
		this.conexaoUsuario.abrirConexao();
		Usuario usuario = null;
		produtoDAO = new ProdutoDAO();
	    postagemDAO = new PostagemDAO();
		String sqlBuscarPorCidade = "SELECT * FROM usuario WHERE cidade=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlBuscarPorCidade);
			prepare.setString(1, cidade);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setApelido(rs.getString("apelido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setListaProduto(produtoDAO.buscarProdutosPorIdUsuario(usuario.getIdUsuario()));
				usuario.setListaPostagem(postagemDAO.buscarPostagemPorIdUsuario(usuario.getIdUsuario()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public Usuario logar(String apelido, String senha) {
		this.conexaoUsuario.abrirConexao();
		Usuario usuario = null;
		String sqlBuscarPorId = "SELECT * FROM usuario WHERE apelido=? AND senha=?";
		
		try {
			PreparedStatement prepare = (PreparedStatement) this.conexaoUsuario.getConexao().prepareStatement(sqlBuscarPorId);
			prepare.setString(1, apelido);
			prepare.setString(2, senha);
			
			ResultSet rs = prepare.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setApelido(rs.getString("apelido"));
				usuario.setSenha(rs.getString("senha"));
				
				
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
