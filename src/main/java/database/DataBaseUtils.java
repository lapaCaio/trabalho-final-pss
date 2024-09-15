package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtils {

    public static final String URL = "jdbc:sqlite:user_creation_and_maintenance.db";
    
    public static void createTables(Connection conn) {
        createTableUsuario(conn);
        createTableMensagem(conn);
        createTableVisualizacaoUsuario(conn);
    }

    // Tabela 'usuario' com as colunas atualizadas
    public static void createTableUsuario(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT NOT NULL," +
                     "password TEXT NOT NULL," +
                     "notUser BOOLEAN NOT NULL," +
                     "dtCreation TEXT NOT NULL," +
                     "type TEXT NOT NULL CHECK(type IN ('USER', 'ADMIN'))," +
                     "authorized BOOLEAN NOT NULL" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'usuario' criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Tabela 'mensagem' com relacionamento com a tabela 'usuario'
    public static void createTableMensagem(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS mensagem (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "titulo TEXT NOT NULL," +
                     "mensagem TEXT NOT NULL," +
                     "dataCriacao TEXT NOT NULL," +
                     "idUsuario INTEGER NOT NULL," +
                     "FOREIGN KEY(idUsuario) REFERENCES usuario(id)" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'mensagem' criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Tabela 'visualizacao_usuario' (UserView) para marcar mensagens como lidas
    public static void createTableVisualizacaoUsuario(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS visualizacao_usuario (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "idUsuario INTEGER NOT NULL," +
                     "idMensagem INTEGER NOT NULL," +
                     "lida BOOLEAN NOT NULL," +
                     "FOREIGN KEY(idUsuario) REFERENCES usuario(id)," +
                     "FOREIGN KEY(idMensagem) REFERENCES mensagem(id)" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'visualizacao_usuario' criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void dropTables(Connection conn) {
    dropTableVisualizacaoUsuario(conn);
    dropTableMensagem(conn);
    dropTableUsuario(conn);
}

public static void dropTableUsuario(Connection conn) {
    String sql = "DROP TABLE IF EXISTS usuario;";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'usuario' removida com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static void dropTableMensagem(Connection conn) {
    String sql = "DROP TABLE IF EXISTS mensagem;";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'mensagem' removida com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static void dropTableVisualizacaoUsuario(Connection conn) {
    String sql = "DROP TABLE IF EXISTS visualizacao_usuario;";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'visualizacao_usuario' removida com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

}
