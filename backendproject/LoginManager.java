package backendproject;

import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class LoginManager {
    private static LoginManager instance;

    private String loggedInUserId; // 로그인한 사용자 ID
    private int seq;
    private DatabaseConnect db;

    private LoginManager() {
        db = DatabaseConnect.getInstance();
    }

    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean login(String inputId, String inputPassword) throws SQLException {
        ResultSet rs = null;
        db.connect();
        String sql = "SELECT seq, user_id, user_password FROM CUSTOMERS WHERE user_id = ?";

        rs = db.read(sql, inputId);

        if (rs != null && rs.next()) {
            if (inputId.equals(rs.getString("user_id"))) {
                if (rs.getString("user_password").equals(inputPassword)) {
                    loggedInUserId = inputId;
                    seq = rs.getInt("seq");
                    return true;
                }
            }
        }

        db.close();
        return false;
    }

    public void logout() {
        loggedInUserId = null; // 로그아웃 시 사용자 ID 초기화
    }

}
