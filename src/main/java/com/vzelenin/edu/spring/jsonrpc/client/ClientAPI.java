package com.vzelenin.edu.spring.jsonrpc.client;

import com.vzelenin.edu.spring.jsonrpc.service.user.User;

public interface ClientAPI {
    User createUser(String userName, String firstName, String password);
    User createUser(String userName, String password);
    User findUserByUserName(String userName);
    int getUserCount();
    void getRuntimeException();
    void getOwnException();
}
