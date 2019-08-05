package com.schibsted.spain.friends.user.validator;

import com.schibsted.spain.friends.user.command.RegisterUserCommand;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RegisterUserCommandValidator implements FriendsCommandValidator<RegisterUserCommand>
{
    @Override
    public void apply(RegisterUserCommand command)
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<RegisterUserCommand>> errors = validatorFactory.getValidator().validate(command);
        if (!CollectionUtils.isEmpty(errors))
        {
            throw new ConstraintViolationException(errors);
        }
    }
}
