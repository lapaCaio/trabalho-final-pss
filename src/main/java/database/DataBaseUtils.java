package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataBaseUtils {

    public static final String URL = "jdbc:sqlite:user_creation_and_maintenance.db";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void createTables(Connection conn) {
    createTableUsuario(conn);
    createTableMensagem(conn);
    createTableVisualizacaoUsuario(conn);
}

public static void createTableUsuario(Connection conn) {
    String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "nome TEXT NOT NULL," +
                 "senha TEXT NOT NULL," +
                 "notificacoesLidas DOUBLE NOT NULL," +
                 "notificacoesRecebidas DOUBLE NOT NULL," +
                 "estado TEXT NOT NULL CHECK(estado IN ('admin', 'common'))," +
                 "aproved BOOLEAN NOT NULL," +
                 "dataCriacao TEXT NOT NULL" +
                 ");";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'usuario' atualizada com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static void createTableMensagem(Connection conn) {
    String sql = "CREATE TABLE IF NOT EXISTS mensagem (" +
                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "titulo TEXT NOT NULL," +
                 "mensagem TEXT NOT NULL," +
                 "dataCriacao TEXT NOT NULL," +
                 "idUsuario INTEGER NOT NULL," +  // Adicionando relacionamento com o usuário
                 "FOREIGN KEY(idUsuario) REFERENCES usuario(id)" +
                 ");";
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sql);
        System.out.println("Tabela 'mensagem' atualizada com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

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
        System.out.println("Tabela 'visualizacao_usuario' atualizada com sucesso.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}


//    public static void insertMockDataIfEmpty(Connection conn) {;
//        String checkSql = "SELECT COUNT(*) AS total FROM mensagem";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(checkSql)) {
//
//            if (rs.next() && rs.getInt("total") == 0) {
//                // Inserir dado mockado na tabela apenas se estiver vazia
//                insertData(conn, "Título Exemplo", "Mensagem de exemplo", LocalDate.now());
//            } else {
//                System.out.println("Tabela já contém dados, inserção de mock ignorada.");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    private static void insertData(Connection conn, String titulo, String mensagem, LocalDate dataCriacao) {;;;;
//        String sql = "INSERT INTO mensagem(titulo, mensagem, dataCriacao) VALUES(?, ?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, titulo);
//            pstmt.setString(2, mensagem);
//            pstmt.setString(3, dataCriacao.format(FORMATTER));
//            pstmt.executeUpdate();
//            System.out.println("Dado mockado inserido com sucesso.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    
    public static void selectData(Connection conn) {
        String sql = "SELECT id, titulo, mensagem, dataCriacao FROM mensagem";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String mensagem = rs.getString("mensagem");
                String dataCriacao = rs.getString("dataCriacao");
                System.out.println("ID: " + id + ", Título: " + titulo + 
                                   ", Mensagem: " + mensagem + ", Data de Criação: " + dataCriacao);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
