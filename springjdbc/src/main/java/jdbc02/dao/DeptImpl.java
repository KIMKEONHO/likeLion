package jdbc02.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DeptImpl implements DeptDao {
    private final JdbcTemplate jdbcTemplate;

    private String etc;

    @Override
    public void insertDept(Dept dept) {
        etc = "insert into dept(dept_name, location) values(?,?)";
        try {
            jdbcTemplate.update(etc, "asd","부산");
        }catch (DataAccessException e){
            throw new DataAccessException("사용자가 잘못된 값을 입력했습니다." + e.getMessage()){};
        }
    }

    @Override
    public List<Dept> findAllDepts() {
        etc = "select * from dept";
        try {
            return jdbcTemplate.query(etc, new BeanPropertyRowMapper<Dept>(Dept.class));
        }catch (DataAccessException e){
            throw new DataAccessException("에러가 발생." + e.getMessage()){};
        }
    }

    @Override
    public void updateDept(Dept dept) {
        etc = "update dept set dept_name=?,location=? where dept_id=?";

        jdbcTemplate.update(etc, dept.getDeptname(), dept.getLocation(), dept.getId());
    }

    @Override
    public void deleteDept(Long id) {
        etc = " delete from dept where dept_id=?";
        jdbcTemplate.update(etc, id);
    }
}
