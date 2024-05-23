package com.semenbazanov.service;

import com.semenbazanov.model.User;

public interface UserService {
    void add(User user);

    User get(String login, String password);

    User get(long id);

    User delete(long id);
}
