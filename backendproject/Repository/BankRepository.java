package backendproject.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankRepository {

    private DatabaseConnect db = DatabaseConnect.getInstance();

    public ArrayList<String> readAllBank() throws SQLException {
        ResultSet rs = null;
        String sql = "select bank_name from bank";
        ArrayList<String> result = new ArrayList<>();

        db.connect();
        rs = db.read(sql);

        if (rs != null) {
            while (rs.next()) {
                result.add(rs.getString("bank_name"));
            }
        }else{
            System.out.println("현재 아무런 은행이 계설되지 않았습니다.");
        }
        db.close();

        return result;
    }
}
