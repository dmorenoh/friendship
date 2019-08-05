package com.schibsted.spain.friends.user.validator;

import com.schibsted.spain.friends.common.command.FriendsCommand;

public interface FriendsCommandValidator<T extends FriendsCommand>
{
    void apply(T command);
}
