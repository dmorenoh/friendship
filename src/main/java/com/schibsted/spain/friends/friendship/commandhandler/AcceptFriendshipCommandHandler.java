package com.schibsted.spain.friends.friendship.commandhandler;

import com.schibsted.spain.friends.common.commandhandler.FriendsCommandHandler;
import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.user.model.User;
import com.schibsted.spain.friends.user.validator.FriendsCommandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class AcceptFriendshipCommandHandler implements FriendsCommandHandler<AcceptFriendshipCommand>
{

    private final FriendsCommandValidator<AcceptFriendshipCommand> validator;


    @Override
    public synchronized void apply(AcceptFriendshipCommand command)
    {
        validator.apply(command);

        User accepter = command.getAccepter();
        User accepted = command.getAccepted();

        accepter.addFriend(accepted);
        accepted.addFriend(accepter);
        accepter.removeFriendshipRequestFrom(accepted);
    }

}
