package com.vzelenin.edu.spring.jsonrpc.service;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.vzelenin.edu.spring.jsonrpc.exceptions.exception.OwnException;
import com.vzelenin.edu.spring.jsonrpc.dao.DAOImpl;
import com.vzelenin.edu.spring.jsonrpc.service.user.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
@AutoJsonRpcServiceImpl
public class UserServiceImpl implements UserService {

    private final DAOImpl database;

    public UserServiceImpl(DAOImpl database) {
        this.database = database;
    }

    public User createUser(String userName, String firstName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setPassword(password);
        database.saveUser(user);
        return user;
    }

    public User createUser(String userName, String password) {
        return createUser(userName, null, password);
    }

    public User findUserByUserName(String userName) {
        return database.findUserByUserName(userName);
    }

    public int getUserCount() throws SQLException {
        return database.getUserCount();
    }

    @Override
    public void getOwnException() {
        throw new OwnException("Throw OwnException from jsonrpc UserServiceImpl");
    }

    @Override
    public void getRuntimeException() {
        throw new NullPointerException("Throw NullPointerException from jsonrpc UserServiceImpl");
    }
}

