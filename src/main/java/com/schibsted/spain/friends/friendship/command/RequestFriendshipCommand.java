package com.schibsted.spain.friends.friendship.command;

import com.schibsted.spain.friends.common.command.FriendsCommand;
import com.schibsted.spain.friends.user.model.User;
import lombok.Value;

@Value
public class RequestFriendshipCommand implements FriendsCommand
{
    User requester;
    User requested;


    public boolean notSelfFriendshipRequest()
    {
        return !requester.equals(requested);
    }


    public boolean notExistingPendingRequest()
    {
        return !requested.hasPendingFriendshipFrom(requester);
    }


    public boolean notFriendsYet()
    {
        return !requested.isFriendOf(requester);
    }
}
