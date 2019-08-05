package com.schibsted.spain.friends.security;

public interface SecurityHandler
{
    void validate(String user, String password);
}
