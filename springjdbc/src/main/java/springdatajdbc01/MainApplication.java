package springdatajdbc01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jdbc.repository.query.Query;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public Book book(){
        return new Book();
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository){
        return (args) -> {

            PageRequest pageRequest = PageRequest.of(0, 5);
            Page<User> pageUsers= userRepository.findAllUsersWithPagination(pageRequest);

            pageUsers.forEach(System.out::println);
        };
    }
}
