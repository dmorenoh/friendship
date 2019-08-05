package com.schibsted.spain.friends.common.commandhandler.impl

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.schibsted.spain.friends.friendship.command.DeclineFriendshipCommand
import com.schibsted.spain.friends.friendship.exception.DeclineFriendshipRequestException
import com.schibsted.spain.friends.friendship.commandhandler.DeclineFriendshipRequestCommandHandler
import com.schibsted.spain.friends.user.model.User
import spock.lang.Specification

class DeclineFriendshipRequestCommandHandlerSpec extends Specification {

    private static final String DECLINED_USERNAME = "userTo"
    private static final String DECLINED_PASSWORD = "pwdUserTo"
    private static final String DECLINER_USERNAME = "userFrom"
    private static final String DECLINER_PASSWORD = "pwdUserFrom"

    @Subject
    DeclineFriendshipRequestCommandHandler handler

    def "should fail when there is not pending friendship request"() {
        given: "a userA as decliner and userB as declined user"
            def userB = User.builder()
                    .username(DECLINED_USERNAME)
                    .password(DECLINED_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()

            def userA = User.builder()
                    .username(DECLINER_USERNAME)
                    .password(DECLINER_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()
        expect: "userA has no pending friendship request from userB"
            !userA.getPendingFriendshipRequest().contains(userB)
        when: "userA wants to decline friendship request from userB"
            handler.apply(new DeclineFriendshipCommand(userA, userB))
        then: "fails"
            thrown DeclineFriendshipRequestException

    }

    def "should succeed when there is pending friendship request"() {
        given: "a userA as decliner and userB as declined user"
            def userB = User.builder()
                    .username(DECLINED_USERNAME)
                    .password(DECLINED_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()

            def userA = User.builder()
                    .username(DECLINER_USERNAME)
                    .password(DECLINER_PASSWORD)
                    .pendingFriendshipRequest(new ArrayList<User>())
                    .friends(new ArrayList<User>()).build()
        and: "userA has pending friendship request from userB"
            userA.getPendingFriendshipRequest().add(userB)
        when: "userA wants to decline friendship request from userB"
            handler.apply(new DeclineFriendshipCommand(userA, userB))
        then: "fails"
            !userA.getPendingFriendshipRequest().contains(userB)

    }
}
