package com.schibsted.spain.friends.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(final String message)
    {
        super(message);
    }
}
