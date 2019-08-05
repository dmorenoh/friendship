package com.schibsted.spain.friends.user.validator;

import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.friendship.exception.FriendshipAcceptanceException;
import org.springframework.stereotype.Component;

@Component
public class AcceptFriendshipCommandValidator implements FriendsCommandValidator<AcceptFriendshipCommand>
{
    @Override
    public void apply(AcceptFriendshipCommand command)
    {
        if (!command.getAccepter().hasPendingFriendshipFrom(command.getAccepted()) ||
            command.getAccepter().isFriendOf(command.getAccepted()))
        {
            throw new FriendshipAcceptanceException("Invalid");
        }
    }
}
