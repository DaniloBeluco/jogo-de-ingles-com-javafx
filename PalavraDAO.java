package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.usuario;
import util.Constants;
import application.palavra;
public class PalavraDAO {

	public void inserir(palavra a) {
		String sql = "insert into palavras (palavra, resposta, dica, imagem) values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, a.getPalavra());
			ps.setString(2, a.getResposta());
			ps.setString(3, a.getDica());
			ps.setBytes(4, a.getImagem());
		
			
			ps.executeUpdate();  //executa o comando sql
			
			System.out.println("Recebendo: "+a.getPalavra());
			System.out.println("Recebendo: "+a.getResposta());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO CONECTAR");
		}
	}
	
	
	public String selecionarPalavra(int id) {                     
		String palavra="";
		String sql = "Select palavra from palavras where id = "+id+"";       
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
			palavra = rs.getString("palavra");	                                                                              
				                                                       
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return palavra;
	}
	
	public String selecionarResposta(int id) {                     
		String resposta="";
		String sql = "Select resposta from palavras where id = "+id+"";       
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
			resposta = rs.getString("resposta");	                                                                              
				                                                       //adiciona na array list tudo
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	public String selecionarDica(int id) {                     
		String dica="";
		String sql = "Select dica from palavras where id = "+id+"";       
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
				dica = rs.getString("dica");	                                                                              
			                                                        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dica;
	}
	
	public byte[] selecionarImagem(int id) {                     
		byte[] imagem = null;
		String sql = "Select imagem from palavras where id = "+id+"";       
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
			imagem = rs.getBytes("imagem");	                                                                              
				                                                       
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imagem;
	}
	
	public int retornarQuantidade() {
		int quantidade=0;
		String sql = "select * from palavras";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
				quantidade++;                                                         
			}
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
	
	
}
