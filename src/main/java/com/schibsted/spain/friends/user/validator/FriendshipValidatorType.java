package com.schibsted.spain.friends.user.validator;

import com.schibsted.spain.friends.common.command.FriendsCommand;
import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand;
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum FriendshipValidatorType
{
    ACCEPT_FRIENDSHIP_VALIDATOR(AcceptFriendshipCommand::areNotFriendsYet, AcceptFriendshipCommand::friendshipRequestExists),
    REQUEST_FRIENDSHIP_VALIDATOR(RequestFriendshipCommand::notSelfFriendshipRequest, RequestFriendshipCommand::notExistingPendingRequest, RequestFriendshipCommand::notFriendsYet),
    DECLINE_FRIENDSHIP_VALIDATOR(DeclineFriendshipCommand::friendshipRequestExists);

    private List<Predicate<? extends FriendsCommand>> evaluators;


    <T extends FriendsCommand> FriendshipValidatorType(final Predicate<T>... evaluators)
    {
        this.evaluators = Arrays.asList(evaluators);
    }


//    public boolean evaluate(FriendsCommand friendsCommand)
//    {
//
//        return evaluators.stream().allMatch(rule -> rule.test(friendsCommand));
//    }

}
