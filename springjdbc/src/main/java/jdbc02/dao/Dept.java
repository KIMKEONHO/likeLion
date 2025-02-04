package jdbc02.dao;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dept {
    private int id;
    private String deptname;
    private String location;
}
