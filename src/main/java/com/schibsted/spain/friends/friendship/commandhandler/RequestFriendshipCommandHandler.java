package com.schibsted.spain.friends.friendship.commandhandler;

import com.schibsted.spain.friends.common.commandhandler.FriendsCommandHandler;
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import com.schibsted.spain.friends.user.model.User;
import com.schibsted.spain.friends.user.validator.FriendsCommandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestFriendshipCommandHandler implements FriendsCommandHandler<RequestFriendshipCommand>
{

    private final FriendsCommandValidator<RequestFriendshipCommand> validator;


    @Override
    public synchronized void apply(final RequestFriendshipCommand command)
    {
        validator.apply(command);
        User requester = command.getRequester();
        User requested = command.getRequested();
        requested.addPendingRequest(requester);
    }

}
