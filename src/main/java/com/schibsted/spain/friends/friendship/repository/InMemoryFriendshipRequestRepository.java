package com.schibsted.spain.friends.friendship.repository;

import com.schibsted.spain.friends.common.data.FriendshipRequestInMemory;
import com.schibsted.spain.friends.friendship.model.FriendshipRequest;
import com.schibsted.spain.friends.user.model.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFriendshipRequestRepository implements FriendshipRequestRepository
{
    @Override
    public void save(FriendshipRequest friendshipRequest)
    {
        friendshipRequest.getTo().getPendingFriendshipRequest().add(friendshipRequest.getFrom());
        FriendshipRequestInMemory.getInstance().save(friendshipRequest);
    }


    @Override
    public List<User> findPendingRequestByUser(String usernameFrom)
    {
        return FriendshipRequestInMemory.getInstance().findPendingRequestByUser(usernameFrom);
    }


    @Override
    public void remove(FriendshipRequest friendshipRequest)
    {
        friendshipRequest.getTo().getPendingFriendshipRequest().remove(friendshipRequest.getFrom());
    }
}
