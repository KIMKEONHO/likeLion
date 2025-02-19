package springjdbc.src.main.java.jdbc01;

import com.example.springjdbc.SpringjdbcApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringjdbcApplication01 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringjdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String sql = "INSERT INTO users(name, email) VALUES(?,?)";

        RowMapper<User> userRowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };


        // RowMapper를 이용하여 결과값을 담아준다
        // BeanPropertyRowMapper - SpringJDBC가 기본으로 제공해주는 RowMapper
//        List<User> users = jdbcTemplate.query("select id,name,emnail from users", new BeanPropertyRowMapper<>(User.class));
        List<User> users = jdbcTemplate.query("select id,name,emnail from users", userRowMapper);

        for (User user : users) {
            System.out.println(user);
        }


    }

    @Autowired
    JdbcTemplate jdbcTemplate;


}
