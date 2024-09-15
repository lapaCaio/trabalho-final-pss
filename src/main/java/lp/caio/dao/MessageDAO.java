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

public class MessageDAO {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void insertMessage(Connection conn, Message message) {
        String sql = "INSERT INTO message(title, content, creationDate) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, message.getTitle());
            pstmt.setString(2, message.getContent());
            pstmt.setString(3, message.getCreationDate().format(FORMATTER));
            pstmt.executeUpdate();
            System.out.println("Message inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Message> selectAllMessages(Connection conn) {
        String sql = "SELECT id, title, content, creationDate FROM message";
        List<Message> messages = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDate creationDate = rs.getObject("creationDate", LocalDate.class);

                Message message = new Message(title, content, creationDate);
                message.setId(id);
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }
}
