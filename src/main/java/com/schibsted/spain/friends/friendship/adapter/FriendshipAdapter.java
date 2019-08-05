package com.schibsted.spain.friends.friendship.adapter;

import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto;
import com.schibsted.spain.friends.friendship.dto.DeclineFrienshipDto;
import com.schibsted.spain.friends.friendship.dto.FriendshipOperationDto;
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto;

public interface FriendshipAdapter<T extends FriendshipOperationDto>
{

    void processFriendshipRequest(RequestFriendshipDto requestFriendshipDto);

    void processAcceptFriendship(AcceptFriendshipDto acceptFriendshipDto);

    void processDeclineFriendship(DeclineFrienshipDto declineFrienshipDto);
}
