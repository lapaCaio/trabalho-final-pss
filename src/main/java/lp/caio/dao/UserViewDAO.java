package lp.caio.dao;

import lp.caio.model.UserView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserViewDAO {

    public void insertUserView(Connection conn, UserView userView) {
        String sql = "INSERT INTO user_view(userId, messageId, read) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userView.getUserId());
            pstmt.setLong(2, userView.getMessageId());
            pstmt.setBoolean(3, userView.isRead());
            pstmt.executeUpdate();
            System.out.println("UserView inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<UserView> selectAllUserViews(Connection conn) {
        String sql = "SELECT id, userId, messageId, read FROM user_view";
        List<UserView> userViews = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                long userId = rs.getLong("userId");
                long messageId = rs.getLong("messageId");
                boolean read = rs.getBoolean("read");

                UserView userView = new UserView(userId, messageId, read);
                userView.setId(id);
                userViews.add(userView);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userViews;
    }
}
