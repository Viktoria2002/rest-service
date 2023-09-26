package com.rest.service;

import com.rest.model.User;

public interface UserService extends Service<User, Long> {
    User save(User user);

    User update(User user);
}
