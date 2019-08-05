package com.schibsted.spain.friends.friendship.repository;

import com.schibsted.spain.friends.friendship.model.FriendshipRequest;
import com.schibsted.spain.friends.user.model.User;
import java.util.List;

public interface FriendshipRequestRepository
{
    void save(FriendshipRequest friendshipRequest);

    List<User> findPendingRequestByUser(String usernameFrom);

    void remove(FriendshipRequest friendshipRequest);

}

