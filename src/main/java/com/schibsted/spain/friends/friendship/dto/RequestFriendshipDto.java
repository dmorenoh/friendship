package com.schibsted.spain.friends.friendship.dto;

import lombok.Value;

@Value
public class RequestFriendshipDto
{
    String usernameFrom;
    String usernameTo;

}
