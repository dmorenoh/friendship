package com.schibsted.spain.friends.friendship.command;

import com.schibsted.spain.friends.common.command.FriendsCommand;
import com.schibsted.spain.friends.user.model.User;
import lombok.Value;

@Value
public class AcceptFriendshipCommand implements FriendsCommand
{
    User accepter;
    User accepted;


    public boolean areNotFriendsYet()
    {
        return !accepter.isFriendOf(accepted);
    }


    public boolean friendshipRequestExists()
    {
        return accepter.hasPendingFriendshipFrom(accepted);
    }
}
