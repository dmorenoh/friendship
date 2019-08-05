package com.schibsted.spain.friends.friendship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class FriendshipAcceptanceException extends RuntimeException
{
    public FriendshipAcceptanceException(final String message)
    {
        super(message);
    }
}
