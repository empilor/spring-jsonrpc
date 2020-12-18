package com.vzelenin.edu.spring.jsonrpc.client;

import com.vzelenin.edu.spring.jsonrpc.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientAPI clientAPI;


    public User findUserByUserName(String name) {
        return clientAPI.findUserByUserName(name);
    }

    public User createUser(String userName, String firstName, String password) {
        return clientAPI.createUser(userName, firstName, password);
    }

    public User createUser(String userName, String password) {
        return clientAPI.createUser(userName, password);
    }

    public int getUserCount() {
        return clientAPI.getUserCount();
    }

    public void getOwnException() {
        clientAPI.getOwnException();
    }

    public void getRuntimeException() {
        clientAPI.getRuntimeException();
    }
}
