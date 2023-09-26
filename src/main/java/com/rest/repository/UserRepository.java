package com.rest.repository;

import com.rest.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);

    User update(User user);
}
