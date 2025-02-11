package com.example.boardproject.repository;

import com.example.boardproject.domain.BoardUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BoardUserRepository extends CrudRepository<BoardUser, Long> {

    @Query("SELECT * FROM BoardUser b WHERE b.user_id = :userId")
    BoardUser findByUserId(@Param("userId") String userId);

}
