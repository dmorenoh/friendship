package com.schibsted.spain.friends.common.commandhandler.impl

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.common.data.UsersInMemory
import com.schibsted.spain.friends.user.command.RegisterUserCommand
import com.schibsted.spain.friends.user.exception.UserUniqueConstraintException
import com.schibsted.spain.friends.user.model.User
import com.schibsted.spain.friends.user.repository.InMemoryUserRepository
import com.schibsted.spain.friends.user.repository.UserRepository
import com.schibsted.spain.friends.user.commandhandler.RegisterUserCommandHandler
import com.schibsted.spain.friends.user.validator.FriendsCommandValidator
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

class RegisterUserCommandHandlerSpec extends Specification {
    public static final String VALID_USERNAME = "username"
    public static final String VALID_PASSWORD = "password"
    @Subject
    RegisterUserCommandHandler registerUserCommandHandler
    @Collaborator
    UserRepository userRepository = new InMemoryUserRepository()
    @Collaborator
    FriendsCommandValidator friendsCommandValidator = Mock()

    def cleanup() {
        UsersInMemory.getInstance().clear()
    }

    @Unroll
    def "should fail when username is like #username and password is like #password"() {
        given: "a user to be registered"
        and: "username is as #username"
        and: "password is as #password"
            def command = new RegisterUserCommand(username, password)
        when: "performs registration"
            registerUserCommandHandler.apply(command)
        then: "fails"
            thrown ConstraintViolationException

        where:
            username       | password
            "sho"          | "pwd"
            VALID_USERNAME | "pwd"
            "sho"          | "password1"
    }

    def "should fail when valid fails"() {
        given: "user to be register"
            def command = new RegisterUserCommand("anyUsername", "anyPassword")
        and: "fails when validates"
            friendsCommandValidator.apply(command) >> { throw new ConstraintViolationException(Collections.emptySet()) }
        when: "performs registration"
            registerUserCommandHandler.apply(command)
        then: "fails"
            thrown ConstraintViolationException
    }


    def "should fail when username is #username and password is #password and user already exists"() {
        given: "registered user user"
            UsersInMemory.getInstance().save(new User(VALID_USERNAME, VALID_PASSWORD, Collections.emptyList(), Collections.emptyList()))
        when: "registered user tries to register again"
            registerUserCommandHandler.apply(new RegisterUserCommand(VALID_USERNAME, VALID_USERNAME))
        then: "fails"
            thrown UserUniqueConstraintException
    }

    def "should succeed when username is #username and password is #password and user does not exist"() {
        expect: "user to be registered does not exist"
            !UsersInMemory.getInstance().getByUsername(VALID_USERNAME).isPresent()
        when: "registered user tries to register again"
            registerUserCommandHandler.apply(new RegisterUserCommand(VALID_USERNAME, VALID_USERNAME))
        then: "succeeds"
            UsersInMemory.getInstance().getByUsername(VALID_USERNAME).isPresent()

    }
}
