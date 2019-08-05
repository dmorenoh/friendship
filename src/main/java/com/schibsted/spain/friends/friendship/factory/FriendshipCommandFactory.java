package com.schibsted.spain.friends.friendship.factory;

import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto;
import com.schibsted.spain.friends.friendship.dto.DeclineFrienshipDto;
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto;

public interface FriendshipCommandFactory
{
    RequestFriendshipCommand createRequestFriendshipCommand(RequestFriendshipDto requestFriendshipDto);

    AcceptFriendshipCommand createAcceptFriendshipCommand(AcceptFriendshipDto acceptFriendshipDto);

    DeclineFriendshipCommand createDeclineFriendshipCommand(DeclineFrienshipDto declineFrienshipDto);
}
