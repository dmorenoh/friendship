package com.schibsted.spain.friends.common.factory

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand
import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto
import com.schibsted.spain.friends.user.exception.UserNotFoundException
import com.schibsted.spain.friends.friendship.factory.DefaultFriendshipCommandFactory
import com.schibsted.spain.friends.user.model.User
import com.schibsted.spain.friends.user.repository.UserRepository
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


class DefaultFriendshipCommandFactorySpec extends Specification {
    public static final String USERNAME_FROM = "requester"
    public static final String USERNAME_TO = "requested"

    @Shared
    def ANY_USER = new User("anyUser", "anyPassword", Collections.emptyList(), Collections.emptyList())

    def USER_FROM = new User("userFrom", "anyPassword", Collections.emptyList(), Collections.emptyList())

    def USER_TO = new User("userTo", "anyPassword", Collections.emptyList(), Collections.emptyList())

    @Subject
    DefaultFriendshipCommandFactory defaultFriendshipCommandFactory

    @Collaborator
    UserRepository userRepository = Mock()

    @Unroll
    def "should fail when userFrom returned as #usedFromReturnedAs and userTo returned as #usedToReturnedAs when creating RequestFriendshipCommand"() {
        given: "a non-existing user"
            userRepository.findByUsername(USERNAME_FROM) >> usedFromReturnedAs
            userRepository.findByUsername(USERNAME_TO) >> usedToReturnedAs

        when: "creates request friendship command"
            defaultFriendshipCommandFactory.createRequestFriendshipCommand(new RequestFriendshipDto(USERNAME_FROM, USERNAME_TO))

        then: "fails as not user found"
            thrown UserNotFoundException

        where:
            usedFromReturnedAs    | usedToReturnedAs
            Optional.empty()      | Optional.empty()
            Optional.of(ANY_USER) | Optional.empty()
            Optional.empty()      | Optional.of(ANY_USER)
    }

    def "should return command when both userFrom and userTo are found"() {
        given: "two existing users"
            userRepository.findByUsername(USERNAME_FROM) >> Optional.of(USER_FROM)
            userRepository.findByUsername(USERNAME_TO) >> Optional.of(USER_TO)

        when: "creates request friendship command"
            def returnedCommand = defaultFriendshipCommandFactory
                    .createRequestFriendshipCommand(new RequestFriendshipDto(USERNAME_FROM, USERNAME_TO))

        then: "return command based of these users"
            returnedCommand == new RequestFriendshipCommand(USER_FROM, USER_TO)
    }


    @Unroll
    def "should fail when userFrom returned as #usedFromReturnedAs and userTo returned as #usedToReturnedAs when creating AcceptFriendshipCommand"() {
        given: "a non-existing user"
            userRepository.findByUsername(USERNAME_FROM) >> usedFromReturnedAs
            userRepository.findByUsername(USERNAME_TO) >> usedToReturnedAs

        when: "creates request friendship command"
            defaultFriendshipCommandFactory.createAcceptFriendshipCommand(new AcceptFriendshipDto(USERNAME_FROM, USERNAME_TO))

        then: "fails as not user found"
            thrown UserNotFoundException

        where:
            usedFromReturnedAs    | usedToReturnedAs
            Optional.empty()      | Optional.empty()
            Optional.of(ANY_USER) | Optional.empty()
            Optional.empty()      | Optional.of(ANY_USER)
    }

    def "should return command when both userFrom and userTo are found when creating AcceptFriendshipCommand"() {
        given: "two existing users"
            userRepository.findByUsername(USERNAME_FROM) >> Optional.of(USER_FROM)
            userRepository.findByUsername(USERNAME_TO) >> Optional.of(USER_TO)

        when: "creates request friendship command"
            def returnedCommand = defaultFriendshipCommandFactory
                    .createAcceptFriendshipCommand(new AcceptFriendshipDto(USERNAME_FROM, USERNAME_TO))

        then: "return command based of these users"
            returnedCommand == new AcceptFriendshipCommand(USER_FROM, USER_TO)
    }

}
