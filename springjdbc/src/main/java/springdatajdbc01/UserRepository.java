package springdatajdbc01;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> , CustomUserRepository {
    public User getUserById(int id);
    public List<User> findAll();

}
