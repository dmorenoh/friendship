package com.schibsted.spain.friends.common.data;

import com.schibsted.spain.friends.friendship.model.FriendshipRequest;
import com.schibsted.spain.friends.user.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendshipRequestInMemory
{
    private Map<String, List<User>> pendingRequest = new HashMap<>();
    private static FriendshipRequestInMemory INSTANCE = null;


    public static FriendshipRequestInMemory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FriendshipRequestInMemory();
        }
        return INSTANCE;
    }


    public List<User> findPendingRequestByUser(final String username)
    {
        if (pendingRequest.containsKey(username))
        {
            return Collections.emptyList();
        }
        return pendingRequest.get(username);
    }


    public void save(final FriendshipRequest friendshipRequest)
    {
        pendingRequest.putIfAbsent(friendshipRequest.getTo().getUsername(), new ArrayList<>());
        pendingRequest.get(friendshipRequest.getTo().getUsername()).add(friendshipRequest.getFrom());
    }

}
