package com.schibsted.spain.friends.friendship.model;

import com.schibsted.spain.friends.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Friendship
{
    private User user;
    private User friend;


    public static Friendship of(User user, User friend)
    {
        return new Friendship(user, friend);
    }
}
