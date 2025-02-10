package com.example.demo.Repository;

import com.example.demo.Domain.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FreindRepository extends CrudRepository<Friend, Long>, PagingAndSortingRepository<Friend, Long> {

}
