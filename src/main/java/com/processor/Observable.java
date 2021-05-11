package com.processor;

import com.domain.User;

public interface Observable {

    void notifyUsers();

    void removeUsers(User user);
}
