package com.schibsted.spain.friends.friendship.command;

import com.schibsted.spain.friends.common.command.FriendsCommand;
import com.schibsted.spain.friends.user.model.User;
import lombok.Value;

@Value
public class DeclineFriendshipCommand implements FriendsCommand
{
    User declinerUser;
    User declinedUser;


    public boolean friendshipRequestExists()
    {
        return declinerUser.hasPendingFriendshipFrom(declinedUser);
    }
}
