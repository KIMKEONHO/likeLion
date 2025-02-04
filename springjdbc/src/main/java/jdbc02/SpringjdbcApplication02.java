package jdbc02;

import jdbc02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringjdbcApplication02 implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringjdbcApplication02.class, args);
    }

    @Autowired
    private UserDao userDao;

    @Override
    public void run(String... args) throws Exception {

    }
}
