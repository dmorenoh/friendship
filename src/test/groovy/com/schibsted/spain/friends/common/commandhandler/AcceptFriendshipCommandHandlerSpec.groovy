package com.schibsted.spain.friends.common.commandhandler


import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.friendship.command.AcceptFriendshipCommand
import com.schibsted.spain.friends.friendship.commandhandler.AcceptFriendshipCommandHandler
import com.schibsted.spain.friends.friendship.exception.FriendshipAcceptanceException
import com.schibsted.spain.friends.user.model.User
import spock.lang.Specification

class AcceptFriendshipCommandHandlerSpec extends Specification {


    private static final String ACCEPTED_USERNAME = "userTo"
    private static final String ACCEPTED_PASSWORD = "pwdUserTo"
    private static final String ACCEPTER_USERNAME = "userFrom"
    private static final String ACCEPTER_PASSWORD = "pwdUserFrom"

    @Subject
    AcceptFriendshipCommandHandler acceptFriendshipCommandHandler


    def "should fail when accepter is already a friend accepted"() {
        given: "User B and User A as friends"
            def userB = User.builder()
                    .username(ACCEPTED_USERNAME)
                    .password(ACCEPTED_PASSWORD)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(new ArrayList<User>()).build()

            def userA = User.builder()
                    .username(ACCEPTER_USERNAME)
                    .password(ACCEPTER_PASSWORD)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(new ArrayList<User>()).build()

            userB.addFriend(userA)
            userA.addFriend(userB)

        when: "user A wants to accept friendship request from user B"
            acceptFriendshipCommandHandler.apply(new AcceptFriendshipCommand(userA, userB))

        then: "fails as they both are already friends"
            thrown FriendshipAcceptanceException
    }

    def "should fail when if there is no pending request from accepted"() {
        given: "a UserA as accepter and UserB as accepted"
            def userB = User.builder()
                    .username(ACCEPTED_USERNAME)
                    .password(ACCEPTED_PASSWORD)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(new ArrayList<User>()).build()

            def userA = User.builder()
                    .username(ACCEPTER_USERNAME)
                    .password(ACCEPTER_PASSWORD)
                    .pendingFriendshipRequest(Collections.emptyList())
                    .friends(new ArrayList<User>()).build()

        expect: "UserA has not pending request from UserB "
            !userA.getPendingFriendshipRequest().contains(userB)

        when: "user A wants to accept friendship request from user B"
            acceptFriendshipCommandHandler.apply(new AcceptFriendshipCommand(userA, userB))

        then: "succeed"
            thrown FriendshipAcceptanceException
    }

    def "should succeed when accepter has pending request from accepted and they both are not friends yet"() {
        given: "userA as accepter and userB as accepted"
            def userB = User.builder()
                    .username(ACCEPTED_USERNAME)
                    .password(ACCEPTED_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()

            def userA = User.builder()
                    .username(ACCEPTER_USERNAME)
                    .password(ACCEPTER_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()
        and: "userA has pending request from userB"
            userA.addPendingRequest(userB)
        when: "userA wants to accept userB"
            acceptFriendshipCommandHandler.apply(new AcceptFriendshipCommand(userA, userB))
        then: "succeed"
            userA.isFriendOf(userB)
            !userA.hasPendingFriendshipFrom(userB)
    }
}
