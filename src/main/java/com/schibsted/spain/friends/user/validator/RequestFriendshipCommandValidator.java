package com.schibsted.spain.friends.user.validator;

import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import com.schibsted.spain.friends.friendship.exception.FriendshipRequestException;
import com.schibsted.spain.friends.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class RequestFriendshipCommandValidator implements FriendsCommandValidator<RequestFriendshipCommand>
{
    @Override
    public void apply(RequestFriendshipCommand command)
    {
        User requester = command.getRequester();
        User requested = command.getRequested();

        if (requester.equals(requested) ||
            requested.hasPendingFriendshipFrom(requester) ||
            requested.isFriendOf(requester))
        {
                throw new FriendshipRequestException("Invalid");
        }
    }
}
