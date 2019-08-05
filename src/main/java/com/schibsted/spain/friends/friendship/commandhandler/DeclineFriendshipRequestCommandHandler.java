package com.schibsted.spain.friends.friendship.commandhandler;

import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand;
import com.schibsted.spain.friends.common.commandhandler.FriendsCommandHandler;
import com.schibsted.spain.friends.friendship.exception.DeclineFriendshipRequestException;
import com.schibsted.spain.friends.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeclineFriendshipRequestCommandHandler implements FriendsCommandHandler<DeclineFriendshipCommand>
{

    @Override
    public void apply(final DeclineFriendshipCommand command)
    {
        User declinerUser = command.getDeclinerUser();
        User declinedUser = command.getDeclinedUser();

        if (!declinerUser.hasPendingFriendshipFrom(declinedUser))
        {
            throw new DeclineFriendshipRequestException("FriendshipRequest request does not exist");
        }

        declinerUser.removeFriendshipRequestFrom(declinedUser);
    }

}
