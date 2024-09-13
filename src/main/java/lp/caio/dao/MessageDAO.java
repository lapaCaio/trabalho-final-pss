/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lp.caio.dao;

import lp.caio.model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class MessageDAO {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    public void insertMensagem(Connection conn, Message mensagem) {
        String sql = "INSERT INTO mensagem(titulo, mensagem, dataCriacao) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mensagem.getTitulo());
            pstmt.setString(2, mensagem.getMensagem());
            pstmt.setString(3, mensagem.getDataCriacao().format(FORMATTER));
            pstmt.executeUpdate();
            System.out.println("Mensagem inserida com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Message> selectAllMensagens(Connection conn) {
        String sql = "SELECT id, titulo, mensagem, dataCriacao FROM mensagem";
        List<Message> mensagens = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String mensagem = rs.getString("mensagem");
                LocalDate dataCriacao = rs.getObject("dataCriacao", LocalDate.class);

                Message msg = new Message(titulo, mensagem, dataCriacao);
                msg.setId(id);
                mensagens.add(msg);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mensagens;
    }
}
