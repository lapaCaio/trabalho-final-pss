package lp.caio.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:mysql://localhost:3306/nome_do_banco";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    // Instância de Connection
    private static Connection connection = null;

    // Construtor privado para evitar instâncias da classe
    private DatabaseConnection() {
    }

    // Método estático para obter a conexão
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Carregar o driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Criar a conexão
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC não encontrado!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados!");
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }

    // Método para fechar a conexão
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados!");
            e.printStackTrace();
        }
    }
}
