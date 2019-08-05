package com.schibsted.spain.friends.user.repository;

import com.schibsted.spain.friends.common.data.UsersInMemory;
import com.schibsted.spain.friends.user.model.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository
{

    @Override
    public void save(User user)
    {
        UsersInMemory instance = UsersInMemory.getInstance();
        instance.save(user);
    }


    @Override
    public Optional<User> findByUsername(String username)
    {
        UsersInMemory instance = UsersInMemory.getInstance();
        return instance.getByUsername(username);
    }
}
