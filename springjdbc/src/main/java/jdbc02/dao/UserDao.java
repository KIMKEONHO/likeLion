package jdbc02.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public void insertUser(User user);
    public void updateUserEmail(String name, String email);
    public void deleteUser(String name);
    public List<User> findAllUsers();
}
