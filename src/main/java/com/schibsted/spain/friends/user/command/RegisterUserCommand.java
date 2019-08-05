package com.schibsted.spain.friends.user.command;

import com.schibsted.spain.friends.common.command.FriendsCommand;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Value;

@Value
public class RegisterUserCommand implements FriendsCommand
{
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{5,10}$")
    String username;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]{8,12}$")
    String password;

}
