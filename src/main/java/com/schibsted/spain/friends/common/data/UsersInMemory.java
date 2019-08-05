package com.schibsted.spain.friends.common.data;

import com.schibsted.spain.friends.user.exception.UserUniqueConstraintException;
import com.schibsted.spain.friends.user.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersInMemory
{
    private Map<String, User> userMap = new HashMap<>();
    private static UsersInMemory INSTANCE = null;


    public static UsersInMemory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new UsersInMemory();
        }
        return INSTANCE;
    }


    public void save(final User user)
    {
        if (userMap.containsKey(user.getUsername()))
        {
            throw new UserUniqueConstraintException("User unique constraint exception");
        }
        this.userMap.put(user.getUsername(), user);
    }


    public Optional<User> getByUsername(final String username)
    {
        return Optional.ofNullable(this.userMap.get(username));
    }

    public void clear(){
        this.userMap.clear();
    }
}
