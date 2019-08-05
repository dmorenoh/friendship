package com.schibsted.spain.friends.common.commandhandler

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand
import com.schibsted.spain.friends.friendship.commandhandler.RequestFriendshipCommandHandler
import com.schibsted.spain.friends.friendship.exception.FriendshipRequestException
import com.schibsted.spain.friends.user.model.User
import com.schibsted.spain.friends.user.validator.FriendsCommandValidator
import spock.lang.Specification

class RequestFriendshipCommandHandlerSpec extends Specification {

    private static final String USER_TO = "userTo"
    private static final String PWD_USER_TO = "pwdUserTo"
    private static final String USER_FROM = "userFrom"
    private static final String PWD_USER_FROM = "pwdUserFrom"

    @Subject
    RequestFriendshipCommandHandler requestFriendshipCommandHandler

    @Collaborator
    FriendsCommandValidator validator = Mock()

    def "should fail request friendship when request friendship command validation fails"() {
        given: "user A and user B"
            def userB = User.builder()
                    .username(USER_TO)
                    .password(PWD_USER_TO)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(Collections.emptyList()).build()

            def userA = User.builder()
                    .username(USER_FROM)
                    .password(PWD_USER_FROM)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(Collections.emptyList()).build()

        and: "user A wants to request friendship to user B"
            def command = new RequestFriendshipCommand(userB, userA)
        and: "command validation fails"
            validator.apply(command) >> { throw new FriendshipRequestException("invalid") }
        when: "user B wants to request friendship to user A again"
            requestFriendshipCommandHandler.apply(command)
        then: "request fails as invalid"
            thrown FriendshipRequestException
    }

    def "should process request friendship if requester is not friend nor having pending request"() {
        given: "user A and user B"
            def userB = User.builder()
                    .username(USER_TO)
                    .password(PWD_USER_TO)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(Collections.emptyList()).build()

            def userA = User.builder()
                    .username(USER_FROM)
                    .password(PWD_USER_FROM)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(Collections.emptyList()).build()

        when: "user B wants to request friendship to user A again"
            requestFriendshipCommandHandler.apply(new RequestFriendshipCommand(userB, userA))

        then: "request fails as invalid"
            userA.getPendingFriendshipRequest().contains(userB)
    }
}
