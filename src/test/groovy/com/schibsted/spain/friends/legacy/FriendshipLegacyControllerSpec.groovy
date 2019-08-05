package com.schibsted.spain.friends.legacy

import com.schibsted.spain.friends.user.model.User
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup

class FriendshipLegacyControllerSpec extends Specification {


    public static final String USERNAME_TO = "requested"

    def setup() {
        standaloneSetup(MockMvcBuilders.standaloneSetup(signupLegacyController))
    }

    def "should fail when not valid user"() {
        given: "a non-registered user"
            def unknownUser = new User("unknown", "pwd", Collections.emptyList(), Collections.emptyList())
            def request = given().header("X-Password", unknownUser.password)
                    .param("requester", unknownUser.username)
                    .param("requested", USERNAME_TO)

        when: "request friendship"
            def response = request.when().post("/friendship/request")

        then: "fail as not registered user"

    }
}
