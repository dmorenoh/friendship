package com.schibsted.spain.friends.user.repository;

import com.schibsted.spain.friends.user.model.User;
import java.util.Optional;

public interface UserRepository
{
    void save(User user);

    Optional<User> findByUsername(String username);

}
