package jdbc02.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

// final이라고 붙어있는 키워드만 생성자를 생성
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    private String etc;

    @Override
    public void insertUser(User user) {
        etc = "insert into users (name, email) values (?,?)";
        jdbcTemplate.update(etc, user.getName(), user.getEmail());

    }

    @Override
    public void updateUserEmail(String name, String email) {

    }

    @Override
    public void deleteUser(String name) {

    }

    @Override
    public List<User> findAllUsers() {
        etc = "select * from users";
        return jdbcTemplate.query(etc, new BeanPropertyRowMapper<>(User.class));
    }


}

