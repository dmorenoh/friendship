package com.schibsted.spain.friends.user.dto;

import com.schibsted.spain.friends.user.model.User;
import lombok.Value;

@Value
public class FriendDto
{
    String username;


    public static FriendDto of(final User user)
    {
        return new FriendDto(user.getUsername());
    }
}
