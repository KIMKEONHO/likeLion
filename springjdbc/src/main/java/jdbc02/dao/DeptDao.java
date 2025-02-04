package jdbc02.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDao {
    void insertDept(Dept dept);
    List<Dept> findAllDepts();
    void updateDept(Dept dept);
    void deleteDept(Long id);
}
