package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.friendship.adapter.FriendshipAdapter;
import com.schibsted.spain.friends.friendship.dto.AcceptFriendshipDto;
import com.schibsted.spain.friends.friendship.dto.DeclineFrienshipDto;
import com.schibsted.spain.friends.user.dto.FriendDto;
import com.schibsted.spain.friends.friendship.dto.RequestFriendshipDto;
import com.schibsted.spain.friends.user.query.QueryUserHandler;
import com.schibsted.spain.friends.security.SecurityHandler;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipLegacyController
{

    private final SecurityHandler securityHandler;
    private final FriendshipAdapter friendshipAdapter;
    private final QueryUserHandler queryUserHandler;


    @PostMapping("/request")
    void requestFriendship(
        @RequestParam("usernameFrom") String usernameFrom,
        @RequestParam("usernameTo") String usernameTo,
        @RequestHeader("X-Password") String password
    )
    {
        securityHandler.validate(usernameFrom, password);
        friendshipAdapter.processFriendshipRequest(new RequestFriendshipDto(usernameFrom, usernameTo));
    }


    @PostMapping("/accept")
    void acceptFriendship(
        @RequestParam("usernameFrom") String usernameFrom,
        @RequestParam("usernameTo") String usernameTo,
        @RequestHeader("X-Password") String password
    )
    {
        securityHandler.validate(usernameFrom, password);
        friendshipAdapter.processAcceptFriendship(new AcceptFriendshipDto(usernameFrom, usernameTo));
    }


    @PostMapping("/decline")
    void declineFriendship(
        @RequestParam("usernameFrom") String usernameFrom,
        @RequestParam("usernameTo") String usernameTo,
        @RequestHeader("X-Password") String password
    )
    {
        securityHandler.validate(usernameFrom, password);
        friendshipAdapter.processDeclineFriendship(new DeclineFrienshipDto(usernameFrom, usernameTo));
    }


    @GetMapping("/list")
    Object listFriends(
        @RequestParam("username") String username,
        @RequestHeader("X-Password") String password
    )
    {
        securityHandler.validate(username, password);
        return queryUserHandler.getFriends(username)
            .stream()
            .map(FriendDto::getUsername)
            .collect(Collectors.toList());

    }
}
