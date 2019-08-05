package com.schibsted.spain.friends.user.commandhandler;

import com.schibsted.spain.friends.common.commandhandler.FriendsCommandHandler;
import com.schibsted.spain.friends.user.command.RegisterUserCommand;
import com.schibsted.spain.friends.user.model.User;
import com.schibsted.spain.friends.user.repository.UserRepository;
import com.schibsted.spain.friends.user.validator.FriendsCommandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserCommandHandler implements FriendsCommandHandler<RegisterUserCommand>
{
    private final UserRepository userRepository;
    private final FriendsCommandValidator<RegisterUserCommand> validator;


    @Override
    public void apply(final RegisterUserCommand registerUserCommand)
    {
        validator.apply(registerUserCommand);
        userRepository.save(User.of(registerUserCommand));
    }

}
