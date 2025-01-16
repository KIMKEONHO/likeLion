package com.example.demo.Repository;

import java.sql.*;

public class DatabaseConnect {

    private final static String jdbcURL = "jdbc:mysql://localhost:3306/BankSystem?serverTimezone=UTC";
    private final static String dbUser = "root";
    private final static String dbPassword = "1234";

    private static volatile DatabaseConnect instance; // 싱글턴 인스턴스

    protected Connection conn = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    // 싱글턴 인스턴스 반환
    // 더블 락 체크 방식
    public static DatabaseConnect getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnect.class) {
                if (instance == null) {
                    instance = new DatabaseConnect();
                }
            }
        }
        return instance;
    }

    // 생성자로 객체 생성을 못하게 막기 위한 private 선언
    private DatabaseConnect() {}

    // DB 연결
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
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
    public void insert(String sql, Object... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            pstmt.executeUpdate();
            System.out.println("Inserted records successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 업데이트
    public void update(String sql, Object... params) {
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
    public void delete(String sql, Object... params) {
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
    public ResultSet read(String sql, Object... params) {
        try {
            connect();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // PreparedStatement 파라미터 설정
    private void setParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
}
