package com.schibsted.spain.friends.common.commandhandler;

import com.schibsted.spain.friends.common.command.FriendsCommand;

public interface FriendsCommandHandler<T extends FriendsCommand>
{
    void apply(T command);
}
