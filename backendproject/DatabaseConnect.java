package backendproject;

import java.sql.*;

public class DatabaseConnect {
    private final static String jdbcURL = "jdbc:mysql://localhost:3306/like_lion_bootcamp?serverTimezone=UTC";
    private final static String dbUser = "jerry6475";
    private final static String dbPassword = "qwer1234";

    protected Connection conn = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    // DB 연결
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database connection failed!", e);
        }
    }

    // 리소스 정리
    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 삽입
    public void insert(String sql, String... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 업데이트
    public void update(String sql, String... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 삭제
    public void delete(String sql, String... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 읽기
    public void read(String sql, String... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Result: " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // PreparedStatement 파라미터 설정
    private void setParameters(PreparedStatement pstmt, String... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
}
