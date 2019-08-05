package com.schibsted.spain.friends.friendship.adapter;

import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import com.schibsted.spain.friends.common.commandhandler.FriendsCommandHandler;
import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto;
import com.schibsted.spain.friends.friendship.dto.DeclineFrienshipDto;
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto;
import com.schibsted.spain.friends.friendship.factory.FriendshipCommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultFriendshipAdapter implements FriendshipAdapter
{
    private final FriendshipCommandFactory friendshipCommandFactory;
    private final FriendsCommandHandler<RequestFriendshipCommand> requestFriendshipCommandHandler;
    private final FriendsCommandHandler<AcceptFriendshipCommand> acceptFriendshipCommandHandler;
    private final FriendsCommandHandler<DeclineFriendshipCommand> declineFriendshipCommandHandler;


    @Override
    public void processFriendshipRequest(final RequestFriendshipDto requestFriendshipDto)
    {
        RequestFriendshipCommand requestFriendshipCommand = friendshipCommandFactory.createRequestFriendshipCommand(requestFriendshipDto);
        requestFriendshipCommandHandler.apply(requestFriendshipCommand);

    }

    @Override
    public void processAcceptFriendship(final AcceptFriendshipDto acceptFriendshipDto)
    {
        AcceptFriendshipCommand acceptFriendshipCommand = friendshipCommandFactory.createAcceptFriendshipCommand(acceptFriendshipDto);
        acceptFriendshipCommandHandler.apply(acceptFriendshipCommand);
    }


    @Override
    public void processDeclineFriendship(final DeclineFrienshipDto declineFrienshipDto)
    {
        DeclineFriendshipCommand declineFriendshipCommand = friendshipCommandFactory.createDeclineFriendshipCommand(declineFrienshipDto);
        declineFriendshipCommandHandler.apply(declineFriendshipCommand);
    }


}
