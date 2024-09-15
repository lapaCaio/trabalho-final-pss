package lp.caio.dao;

import lp.caio.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class UserDAO {
    
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, senha, notUser, autorizado, dataCriacao, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setBoolean(3, user.isNotUser());
            pstmt.setBoolean(4, user.isAuthorized());
            pstmt.setDate(5, Date.valueOf(user.getDtCreation())); // Converter LocalDate para java.sql.Date
            pstmt.setString(6, user.getType().toString());
            pstmt.executeUpdate();
        }
    }

    public List<User> selectAllUsers(Connection conn) {
        String sql = "SELECT id, name, password, notUser, dtCreation, type, authorized FROM usuario";
        List<User> users = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                boolean notUser = rs.getBoolean("notUser");
                LocalDate dtCreation = LocalDate.parse(rs.getString("dtCreation"), FORMATTER);
                User.Type type = User.Type.valueOf(rs.getString("type"));
                boolean authorized = rs.getBoolean("authorized");

                User user = new User(name, password, notUser, dtCreation, type);
                user.setId(id);
                user.setAuthorized(authorized);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
    
     public boolean hasUsers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
     
      public void createUser(User user) throws SQLException {
        insertUser(user); // Reutiliza o método insertUser
    }
      
      public List<User> getUsersByAuthorizationStatus(boolean status) throws SQLException {
    String sql = "SELECT id, name, password, notUser, dtCreation, type, authorized FROM usuarios WHERE autorizado = ?";
    List<User> users = new ArrayList<>();

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setBoolean(1, status);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                boolean notUser = rs.getBoolean("notUser");
                LocalDate dtCreation = LocalDate.parse(rs.getString("dtCreation"), FORMATTER);
                User.Type type = User.Type.valueOf(rs.getString("type"));
                boolean authorized = rs.getBoolean("authorized");

                User user = new User(name, password, notUser, dtCreation, type);
                user.setId(id);
                user.setAuthorized(authorized);
                users.add(user);
            }
        }
    }

    return users;
}
      
      public void updateUserAuthorizationStatus(long userId, boolean newStatus) throws SQLException {
    String sql = "UPDATE usuarios SET autorizado = ? WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setBoolean(1, newStatus);
        pstmt.setLong(2, userId);
        pstmt.executeUpdate();
        System.out.println("Authorization status updated successfully.");
    }
}

      
      public void updateUserPassword(long userId, String newPassword) throws SQLException {
    String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, newPassword);
        pstmt.setLong(2, userId);
        pstmt.executeUpdate();
        System.out.println("Password updated successfully.");
    }
}


    
         public User getUserByName(String name) throws SQLException {
        String query = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getBoolean("notUser"),
                        rs.getDate("dtCreation").toLocalDate(),
                        User.Type.valueOf(rs.getString("type"))
                    );
                    user.setId(rs.getLong("id"));
                    user.setAuthorized(rs.getBoolean("authorized"));
                    return user;
                }
            }
        }
        return null;
    }
}
