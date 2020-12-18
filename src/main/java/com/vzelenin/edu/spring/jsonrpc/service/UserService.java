package com.vzelenin.edu.spring.jsonrpc.service;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.vzelenin.edu.spring.jsonrpc.service.user.User;

import java.sql.SQLException;

@JsonRpcService("/jsonrpc/UserService")
public interface UserService {
    User createUser(String userName, String firstName, String password);
    User createUser(String userName, String password);
    User findUserByUserName(String userName);
    int getUserCount() throws SQLException;
    void getRuntimeException();
    void getOwnException();
}