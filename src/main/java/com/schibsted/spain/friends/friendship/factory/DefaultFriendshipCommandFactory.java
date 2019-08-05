package com.schibsted.spain.friends.friendship.factory;

import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto;
import com.schibsted.spain.friends.friendship.dto.DeclineFrienshipDto;
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto;
import com.schibsted.spain.friends.user.exception.UserNotFoundException;
import com.schibsted.spain.friends.user.model.User;
import com.schibsted.spain.friends.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultFriendshipCommandFactory implements FriendshipCommandFactory
{
    private final UserRepository userRepository;


    @Override
    public RequestFriendshipCommand createRequestFriendshipCommand(final RequestFriendshipDto requestFriendshipDto)
    {
        return new RequestFriendshipCommand(
            getUser(requestFriendshipDto.getUsernameFrom()),
            getUser(requestFriendshipDto.getUsernameTo())
        );
    }


    @Override
    public AcceptFriendshipCommand createAcceptFriendshipCommand(final AcceptFriendshipDto acceptFriendshipDto)
    {
        return new AcceptFriendshipCommand(
            getUser(acceptFriendshipDto.getUsernameFrom()),
            getUser(acceptFriendshipDto.getUsernameTo())
        );
    }


    @Override
    public DeclineFriendshipCommand createDeclineFriendshipCommand(final DeclineFrienshipDto declineFrienshipDto)
    {
        return new DeclineFriendshipCommand(
            getUser(declineFrienshipDto.getUsernameFrom()),
            getUser(declineFrienshipDto.getUsernameTo()));
    }


    private User getUser(final String usernameFrom)
    {
        return userRepository
            .findByUsername(usernameFrom)
            .orElseThrow(() -> new UserNotFoundException(usernameFrom + " not found"));
    }
}
