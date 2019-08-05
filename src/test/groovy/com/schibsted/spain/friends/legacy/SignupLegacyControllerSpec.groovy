package com.schibsted.spain.friends.legacy


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification
import spock.lang.Unroll

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SignupLegacyControllerSpec extends Specification {

    @LocalServerPort
    private int port

    @Unroll
    def "should return bad request when username is #username and password is #password"() {
        given: "a request with username as #username and password as #password"
            def request = given().port(port)
                    .header("X-Password", password)
                    .queryParam("username", username)

        when: "request signup"
            def response = request.when().post("/signup")

        then: "returns bad request"
            response.then().statusCode(statusCode)

        where:
            username   | password   || statusCode
            "short"    | "pwd"      || 400
            "username" | "password" || 200

    }
}
