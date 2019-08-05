package com.schibsted.spain.friends.friendship.model;

import com.schibsted.spain.friends.user.model.User;
import lombok.Data;

@Data
public class FriendshipRequest
{
    private final User from;
    private final User to;
    private FriendshipRequestStatus status;


    public static FriendshipRequest of(final User from, final User to)
    {
        FriendshipRequest friendship = new FriendshipRequest(from, to);
        friendship.status = FriendshipRequestStatus.REQUESTED;
        return friendship;
    }


    public void accept()
    {
        this.status = FriendshipRequestStatus.ACCEPTED;
    }


    public boolean isAccepted()
    {
        return this.status == FriendshipRequestStatus.ACCEPTED;
    }
}
