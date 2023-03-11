package br.edu.fesa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    //private final ResourceBundle BUNDLE = ResourceBundle.getBundle("derb", new Locale("pt", "BR"));
    private static Conexao conexao;

    private Conexao() {
    }

    public static Conexao getInstance() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/db_hosptech", "usuario", "senha");
        return connection;
    }
}
