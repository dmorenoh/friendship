package com.schibsted.spain.friends.security;

import com.schibsted.spain.friends.common.data.UsersInMemory;

import com.schibsted.spain.friends.common.exception.CustomAuthenticationException;
import com.schibsted.spain.friends.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class FrienshipSecurtyHandler implements SecurityHandler
{
    @Override
    public void validate(String username, String password)
    {
        User userFound = UsersInMemory.getInstance().getByUsername(username).orElseThrow(() -> new CustomAuthenticationException("invalid user"));
        if (!userFound.getPassword().equals(password))
        {
            throw new CustomAuthenticationException("invalid password");
        }
    }
}
