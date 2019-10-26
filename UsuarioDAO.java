package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.usuario;

import util.Constants;

public class UsuarioDAO {

	public void inserir(usuario a) {
		java.util.Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		System.out.println("DATA ATUAL: "+dStr);
		
		String sql = "insert into usuario (nome, pontuacao, data) values (?, ?, ?)";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setString(1, a.getNome());
			ps.setInt(2, a.getPontos());
			ps.setString(3, dStr);
			ps.executeUpdate();  //executa o comando sql
			
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO CONECTAR");
		}
	}
	
	public void inserirUpdate(usuario a) {
		java.util.Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		System.out.println("DATA ATUAL: "+dStr);
		String sql = "update usuario set pontuacao=?, data=? where nome=?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);
			ps.setInt(1, a.getPontos());
			ps.setString(2, dStr);
			ps.setString(3, a.getNome());
		
			ps.executeUpdate();  //executa o comando sql
	
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO CONECTAR");
		}
	}
	
	
	
	public ArrayList<usuario> listarTodas() {            
		
		ArrayList<usuario> lista = new ArrayList<usuario>();
		String sql = "Select * from usuario order by pontuacao desc";       //seleciona tudo da agencia
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
				usuario a = new usuario();                                                                                  
				a.setId(rs.getInt("id"));                                               //array list recebe o registro do banco de dados
				a.setNome(rs.getString("nome"));
				a.setPontos(rs.getInt("pontuacao"));
				a.setData(rs.getString("data"));
				lista.add(a);                                                             //adiciona na array list tudo
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public int retornarQuantidade(String nome) {
		int quantidade=0;
		String sql = "select * from usuario where nome = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
				quantidade++;                                                         
			}
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
	public int retornarPontuacao(String nome) {
		int pontuacao=0;
		String sql = "select * from usuario where nome = ?";
		try {
			PreparedStatement ps = Constants.conn.prepareStatement(sql);                //prepara o sql
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();                                           //rs recebe os registros (eu acho)
			while (rs.next()) {                                                         //enquanto há registros
				pontuacao=rs.getInt("pontuacao");                                                         
			}
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pontuacao;
	}
	

}
