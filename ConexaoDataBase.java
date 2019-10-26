package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDataBase {

	public static Connection conectaBd() {                              //estabelece uma conexão com o banco
		Connection conn = null;
		try {
			File f = new File("db\\english_quizz.sqlite");
			if (f.exists()) {
				Class.forName("org.sqlite.JDBC");                           //carrega o driver do bd
				conn = DriverManager.getConnection("jdbc:sqlite:db\\english_quizz.sqlite");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NA CLASSE CONEXÃO");
		}
		return conn;
	}
}
