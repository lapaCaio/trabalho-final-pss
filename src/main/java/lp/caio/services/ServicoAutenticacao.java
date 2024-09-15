package lp.caio.services;

import lp.caio.dao.UserDAO;
import lp.caio.model.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class ServicoAutenticacao {

    private UserDAO userDAO;

    public ServicoAutenticacao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean hasUsers() throws SQLException {
        return userDAO.hasUsers();
    }

    public void createUser(User user) throws SQLException {
        userDAO.createUser(user);
    }

    public boolean autenticar(String userName, String password) throws SQLException {
        User user = userDAO.getUserByName(userName);
        return user != null && user.getPassword().equals(password);
    }

    public User getUser(String userName) throws SQLException {
        return userDAO.getUserByName(userName);
    }

    public boolean isAdmin(User user) {
        return user.getType() == User.Type.ADMIN;
    }

    public void inicializarAdmin(String userName, String password) throws SQLException {
        if (!hasUsers()) {
            User admin = new User(userName, password, false, LocalDate.now(), User.Type.ADMIN);
            createUser(admin);
        }
    }
}
