package com.vzelenin.edu.spring.jsonrpc.dao;

import com.vzelenin.edu.spring.jsonrpc.config.AppConfig;
import com.vzelenin.edu.spring.jsonrpc.dao.util.Queries;
import com.vzelenin.edu.spring.jsonrpc.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

@Component
public class DAOImpl {
    public static final String DB_URL_PROP = "db.url";
    public static final String DB_USER_PROP = "db.user";
    public static final String DB_PASSWORD_PROP = "db.password";
    private final AppConfig config;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DAOImpl(@Autowired AppConfig appConfig) {
        this.config = appConfig;
    }

    @PostConstruct
    public void initProperties() {
        dbUrl = config.getProperty(DB_URL_PROP);
        dbUser = config.getProperty(DB_USER_PROP);
        dbPassword = config.getProperty(DB_PASSWORD_PROP);
    }

    private String getQuery(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append(" ");
        }
        String query = sb.toString();
        return query;
    }

    public void saveUser(User user) {
        try(Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(Queries.CREATE_NEW_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getPassword());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("An error occurred creating user: " + ex);
            ex.printStackTrace();
        }
    }

    public User findUserByUserName(String userName) {
        try(Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(Queries.FIND_USER_BY_USER_NAME)) {
            statement.setString(1, userName);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            User user = new User();
            while(rs.next()) {
                String firstName = rs.getString(2);
                String password = rs.getString(3);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setPassword(password);
            }
            return user;
        } catch (SQLException ex) {
            System.out.println("An error occurred selecting user: " + ex);
            ex.printStackTrace();
            return null;
        }
    }

    public int getUserCount() throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(Queries.SELECT_USERS_COUNT)) {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            int userCount = 0;
            while(rs.next()) {
                userCount = rs.getInt(1);
            }
            return userCount;
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
