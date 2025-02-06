package springdatajdbc01;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {
    public Page<User> findAllUsersWithPagination(Pageable pageable);
}
