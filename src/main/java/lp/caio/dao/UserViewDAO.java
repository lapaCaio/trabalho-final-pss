/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lp.caio.dao;

import lp.caio.model.UserView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class UserViewDAO {
    public void insertVisualizacaoUsuario(Connection conn, UserView visualizacaoUsuario) {
        String sql = "INSERT INTO visualizacao_usuario(idUsuario, idMensagem, lida) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, visualizacaoUsuario.getIdUsuario());
            pstmt.setLong(2, visualizacaoUsuario.getIdMensagem());
            pstmt.setBoolean(3, visualizacaoUsuario.isLida());
            pstmt.executeUpdate();
            System.out.println("Visualização de usuário inserida com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<UserView> selectAllVisualizacoes(Connection conn) {
        String sql = "SELECT id, idUsuario, idMensagem, lida FROM visualizacao_usuario";
        List<UserView> visualizacoes = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("id");
                long idUsuario = rs.getLong("idUsuario");
                long idMensagem = rs.getLong("idMensagem");
                boolean lida = rs.getBoolean("lida");

                UserView visualizacao = new UserView(idUsuario, idMensagem, lida);
                visualizacao.setId(id);
                visualizacoes.add(visualizacao);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return visualizacoes;
    }
}
