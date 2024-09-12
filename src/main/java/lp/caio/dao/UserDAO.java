package lp.caio.dao;

import java.util.List;
import lp.caio.model.User;

public interface UserDAO {
    void addUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
