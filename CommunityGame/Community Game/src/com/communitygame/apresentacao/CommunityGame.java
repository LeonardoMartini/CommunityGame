package com.communitygame.apresentacao;

import java.util.Scanner;

//import com.communitygame.model.Avaliacao;
//import com.communitygame.model.Favoritos;
//import com.communitygame.model.Mensagem;
import com.communitygame.model.Postagem;
import com.communitygame.model.Produto;
import com.communitygame.model.Seguidores;
import com.communitygame.model.Usuario;
//import com.communitygame.persistencia.AvaliacaoDAO;
//import com.communitygame.persistencia.FavoritosDAO;
//import com.communitygame.persistencia.MensagemDAO;
import com.communitygame.persistencia.PostagemDAO;
import com.communitygame.persistencia.ProdutoDAO;
import com.communitygame.persistencia.SeguidoresDAO;
import com.communitygame.persistencia.UsuarioDAO;

public class CommunityGame {

	

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Postagem postagem = new Postagem();
		PostagemDAO postagemDAO = new PostagemDAO();
		Produto produto = new Produto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Seguidores seguidores = new Seguidores();
		SeguidoresDAO seguidoresDAO = new SeguidoresDAO();
		Long idUsuario;
				
		System.out.println("Bem vindo ao CommunityGame! Um app desenvolvido para facilitar a troca/venda de jogos/acessorios "
				+ "gamers entre jogadores de todo o mundo!");
		System.out.println("Para Logar digite 1; Para se Cadastrar digite 2.");
		int logarOuCadastrar = t.nextInt();
		
		while (true) {
			if (logarOuCadastrar == 2) {
				System.out.println("Digite seu nome:");
				String nome = t.next();
				usuario.setNome(nome);
				
				System.out.println("Digite um apelido, podendo conter letras, numeros, caracteres especiais:");
				String apelido = t.next();
				usuario.setApelido(apelido);
				
				System.out.println("Digite seu email:");
				String email = t.next();
				usuario.setEmail(email);
				
				System.out.println("Digite o estado que você mora:");
				String estado = t.next();
				usuario.setEstado(estado);
				
				System.out.println("Digite a cidade que você mora:");
				String cidade = t.next();
				usuario.setCidade(cidade);
				
				System.out.println("Digite uma senha, e não se esqueça dela!");
				String senha = t.next();
				usuario.setSenha(senha);
				
				usuarioDAO.salvar(usuario);
				
				System.out.println("Para Logar digite 1; Para se Cadastrar digite 2.");
				logarOuCadastrar = t.nextInt();
				
			} else if (logarOuCadastrar == 1){
				System.out.println("Digite seu apelido:");
				String apelido = t.next();
				
				System.out.println("Digite sua senha:");
				String senha = t.next();
				
				usuario = usuarioDAO.logar(apelido, senha);
				
				if(usuario == null) {
					System.out.println("Apelido ou senha invalidos, tente novamente.");
				} else {
					
					int escolha;
												
					System.out.println("Bem vindo " + apelido);
					
					System.out.println(usuarioDAO.buscarPorApelido(apelido));
					
					
					System.out.println("Para editar sua conta digite 1.");
					System.out.println("Para excluir sua conta digite 2.");
					System.out.println("Para buscar um usuario pelo apelido digite 3.");
					System.out.println("Para buscar um usuario pela cidade digite 4.");
					System.out.println("Para listar todos os usuarios digite 5.");
					System.out.println("Para adicionar um produto digite 6.");
					System.out.println("Para editar um produto digite 7.");
					System.out.println("Para excluir um produto digite 8.");
					System.out.println("Para listar todos os produtos digite 9.");
					System.out.println("Para buscar um produto pelo seu id digite 10.");
					System.out.println("Para buscar produtos de um usuario digite 11.");					
					System.out.println("Para criar uma postagem digite 12.");
					System.out.println("Para editar uma postagem digite 13.");
					System.out.println("Para excluir uma postagem digite 14.");
					System.out.println("Para listar todas as postagens digite 15.");
					System.out.println("Para buscar uma postagem por titulo digite 16.");
					System.out.println("Para buscar uma postagem pelo seu id digite 17.");
					System.out.println("Para buscar as postagens de um usuario digite 18.");
					
					System.out.println("Para sair do app digite qualquer outro numero.");
					
					escolha = t.nextInt();
					
					switch(escolha) {
					case 1:
						
						usuario.setIdUsuario(1L);
						
						System.out.println("Nome:");
						String nome = t.next();
					    usuario.setNome(nome);
					    
					    System.out.println("Apelido:");
					    apelido = t.next();
					    usuario.setApelido(apelido);
					    
					    System.out.println("senha:");
					    senha = t.next();
					    usuario.setSenha(senha);
					    
					    System.out.println("E-mail:");
					    String email = t.next();
					    usuario.setEmail(email);
					    
					    System.out.println("Estado:");
					    String estado = t.next();
					    usuario.setEstado(estado);
					    
					    System.out.println("Cidade:");
					    String cidade = t.next();
					    usuario.setCidade(cidade);
					    
					    usuarioDAO.editar(usuario);
						
						break;
						
					case 2:
						
						System.out.println("Digite seu id.");
						long id = t.nextLong();
						usuarioDAO.excluir(id);
						
						
						
						break;
						
					case 3:
						
						System.out.println("Digite o apelido do usuario:");
						apelido = t.next();
						usuario = usuarioDAO.buscarPorApelido(apelido);
						
						System.out.println(usuario);
						
						break;
						
					case 4:
						
						System.out.println("Digite a cidade:");
						cidade = t.next();
						usuario = usuarioDAO.buscarPorCidade(cidade);
						System.out.println(usuario);
						
						break;
						
					case 5:
						
						System.out.println(usuarioDAO.buscarTodos());

						break;
						
					case 6:
						
						System.out.println("Digite o nome do produto:");
						String tipoProduto = t.next();
						produto.setTipoProduto(tipoProduto);
						
						System.out.println("De uma descrição para seu produto:");
						String descricao = t.next();
						produto.setDescricao(descricao);
						
						System.out.println("Digite o seu id.");
						idUsuario  = t.nextLong();
						
						
						usuario.setIdUsuario(idUsuario);
						produto.setUsuario(usuario);
						
						produtoDAO.salvar(produto);
					
						break;
						
					case 7:
						
						System.out.println("Digite o nome do produto:");
						tipoProduto = t.next();
						
						System.out.println("De uma descrição para seu produto:");
						descricao = t.next();
						
						System.out.println("Digite o id do produto a ser editado:");
						Long idProduto = t.nextLong();
						
						produto = produtoDAO.buscarPorId(idProduto);
						
						produto.setTipoProduto(tipoProduto);
						produto.setDescricao(descricao);
						produto.setIdProduto(idProduto);

						
						produtoDAO.editar(produto);
						
						break;
						
					case 8:
						
						System.out.println("Digite o id do produto:");
						id = t.nextLong();
						produtoDAO.excluir(id);
						
						break;
						
					case 9:
						
						produtoDAO.buscarTodos();
						System.out.println(produtoDAO);
						
						break;
						
					case 10:
						
						System.out.println("Digite o id do produto:");
						id = t.nextLong();
						System.out.println(produtoDAO.buscarPorId(id));
						
						break;
						
					case 11:
						
						System.out.println("Digite o id do usuario a ser procurado:");
						idUsuario = t.nextLong();
						System.out.println(produtoDAO.buscarProdutosPorIdUsuario(idUsuario));
						
						break;
						
					case 12:
						
						postagem.setIdPostagem(1L);

						
						System.out.println("De uma descrição a sua postagem:");
						String descricaoPostagem = t.next();
						
						System.out.println("Digite a data da postagem:");
						String data = t.next();
						
						System.out.println("Digite o valor do produto a ser postado:");
						double valor = t.nextDouble();
						
						System.out.println("Digite o nome do produto:");
						String titulo = t.next();
						
						System.out.println("Digite o id do seu produto:");
						Produto pro = new Produto();
						int idPost = t.nextInt();
						pro.setIdProduto(idPost);
						
						System.out.println("Digite o id do seu usuario");
						Usuario usu = new Usuario();
						int idUsu = t.nextInt();
						usu.setIdUsuario(idUsu);
						
						postagem.setDescricaoPostagem(descricaoPostagem);
						postagem.setDataPostagem(data);
						postagem.setValor(valor);
						postagem.setTitulo(titulo);
						postagem.setPostagemCriada(usu);
						postagem.setProdutoPostagem(pro);
						
						postagemDAO.salvar(postagem);
						
						break;
						
					case 13:
						
						System.out.println("De uma descrição a sua postagem:");
						descricaoPostagem = t.next();
						
						System.out.println("Digite a data da postagem:");
						data = t.next();
						
						System.out.println("Digite o valor do produto a ser postado:");
						valor = t.nextDouble();
						
						System.out.println("Digite o nome do produto:");
						titulo = t.next();
						
						System.out.println("Digite o id do seu produto:");
						Produto prod = new Produto();
						int idPos = t.nextInt();
						prod.setIdProduto(idPos);
						
						System.out.println("Digite o id do seu usuario");
						Usuario usua = new Usuario();
						int idUsua = t.nextInt();
						usua.setIdUsuario(idUsua);
						
						System.out.println("Digite o id da sua postagem");
						int idPostagem  = t.nextInt();
						usua.setIdUsuario(idPostagem);
												
						postagem.setDescricaoPostagem(descricaoPostagem);
						postagem.setDataPostagem(data);
						postagem.setValor(valor);
						postagem.setTitulo(titulo);
						postagem.setProdutoPostagem(prod);
						postagem.setPostagemCriada(usua);
						postagem.setIdPostagem(idPostagem);
						
						postagemDAO.editar(postagem);
						
						break;
						
					case 14:
						
						System.out.println("Digite o id da postagem a ser exluida:");
						id = t.nextLong();
						postagemDAO.excluir(id);
						
						break;
						
					case 15:
						
						System.out.println(postagemDAO.buscarTodos());
						
						break;
						
					case 16:
						
						System.out.println("Digite o nome do produto:");
						titulo = t.next();
						System.out.println(postagemDAO.buscarPorTitulo(titulo));
						
						break;
						
					case 17:
						
						System.out.println("Digite o id da postagem a ser procurada:");
						id = t.nextLong();
						System.out.println(postagemDAO.buscarPorId(id));
						
						break;
						
					case 18:
						
						System.out.println("Digite o id do usuario a ser pesquisado:");
						idUsuario = t.nextLong();
						System.out.println(postagemDAO.buscarPostagemPorIdUsuario(idUsuario));
						
						break;
						
					default:
						break;
							
					} 
					
				}
			} else {
				System.out.println("Escolha uma opção válida: 1 para se Logar ou 2 para se Cadastrar.");
				logarOuCadastrar = t.nextInt();
			}
		}
		
	}

}
