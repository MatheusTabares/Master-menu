package br.com.mastermenu.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
	public static Connection abrirConexao() throws Exception{
        Connection conexao;
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost/mastermenu?serverTimezone=UTC", "root", "");
        
        return conexao;
    }
}
