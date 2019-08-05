package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.user.command.RegisterUserCommand;
import com.schibsted.spain.friends.user.commandhandler.RegisterUserCommandHandler;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
@Validated
public class SignupLegacyController
{
    private final RegisterUserCommandHandler handler;


    @PostMapping
    void signUp(
        @RequestParam("username") String username,
        @RequestHeader("X-Password") String password
    )
    {
        handler.apply(new RegisterUserCommand(username, password));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorMessage exceptionHandler(ConstraintViolationException e)
    {
        return new ErrorMessage("400", e.getMessage());

    }
}
