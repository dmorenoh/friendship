package com.schibsted.spain.friends.user.model;

import com.schibsted.spain.friends.user.command.RegisterUserCommand;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
@Builder
public class User
{
    private final String username;
    private final String password;
    private final List<User> friends;
    private final List<User> pendingFriendshipRequest;


    public void addFriend(final User user)
    {
        this.friends.add(user);
    }


    public void addPendingRequest(final User user)
    {
        this.pendingFriendshipRequest.add(user);
    }


    public void removeFriendshipRequestFrom(final User user)
    {
        getPendingFriendshipRequest().remove(user);
    }


    public boolean hasPendingFriendshipFrom(final User user)
    {
        return this.pendingFriendshipRequest.contains(user);
    }


    public boolean isFriendOf(final User user)
    {
        return this.friends.contains(user);
    }


    public static User of(final RegisterUserCommand registerUserCommand)
    {
        return User.builder()
            .username(registerUserCommand.getUsername())
            .password(registerUserCommand.getPassword())
            .pendingFriendshipRequest(new ArrayList<>())
            .friends(new ArrayList<>()).build();
    }
}
