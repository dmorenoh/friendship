package com.schibsted.spain.friends.friendship.repository;

import com.schibsted.spain.friends.friendship.model.Friendship;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFriendshipRepository implements FriendshipRepository
{
    @Override
    public void save(Friendship friendship)
    {
        friendship.getUser().addFriend(friendship.getFriend());
        friendship.getFriend().addFriend(friendship.getUser());
    }
}
