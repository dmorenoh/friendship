package com.schibsted.spain.friends.user.validator

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.friendship.command.RequestFriendshipCommand
import com.schibsted.spain.friends.friendship.exception.FriendshipRequestException
import com.schibsted.spain.friends.user.model.User
import spock.lang.Specification

class RequestFriendshipCommandValidatorSpec extends Specification {
    private static final String USER_TO = "userTo"
    private static final String PWD_USER_TO = "pwdUserTo"
    private static final String USER_FROM = "userFrom"
    private static final String PWD_USER_FROM = "pwdUserFrom"

    @Subject
    RequestFriendshipCommandValidator validator

    def "should fail when requester and requested are the same"() {
        given: "a user"
            def anyUser = User.builder()
                    .username("anyUser")
                    .password("anyPassword")
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(Collections.emptyList()).build()
        and: "user wants to request friendship to her/him self"
            def requestFriendshipCommand = new RequestFriendshipCommand(anyUser, anyUser)
        when: "validates friendship to him/her self"
            validator.apply(requestFriendshipCommand)
        then: "fails"
            thrown FriendshipRequestException
    }

    def "should fail when requested user has already pending friendship request from requester"() {
        given: "user A which has an existing request from user B"
            def userB = User.builder()
                    .username(USER_TO)
                    .password(PWD_USER_TO)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(Collections.emptyList()).build()

            def userA = User.builder()
                    .username(USER_FROM)
                    .password(PWD_USER_FROM)
                    .pendingFriendshipRequest([userB])
                    .friends(Collections.emptyList()).build()

            userA.getPendingFriendshipRequest().add(userB)

        when: "user B wants to request friendship to user A again"
            validator.apply(new RequestFriendshipCommand(userB, userA))

        then: "request fails as invalid"
            thrown FriendshipRequestException
    }

    def "should fail when requester is friend already"() {
        given: "user A which is friend of user B"
            def userB = User.builder()
                    .username(USER_TO)
                    .password(PWD_USER_TO)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(Collections.emptyList()).build()

            def userA = User.builder()
                    .username(USER_FROM)
                    .password(PWD_USER_FROM)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends([userB]).build()

        when: "user B wants to request friendship to user A again"
            validator.apply(new RequestFriendshipCommand(userB, userA))

        then: "request fails as invalid"
            thrown FriendshipRequestException
    }

    def "should do nothing when all constraints are valid"() {
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
            def command =  new RequestFriendshipCommand(userB, userA)
        when: "validates command"
            validator.apply(command)
        then: "succeed"
            noExceptionThrown()
    }
}
