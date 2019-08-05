package com.schibsted.spain.friends.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CustomAuthenticationException extends RuntimeException
{
    public CustomAuthenticationException(final String message)
    {
        super(message);
    }
}
