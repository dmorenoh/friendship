package com.schibsted.spain.friends.friendship.dto;

import lombok.Value;

@Value
public class DeclineFrienshipDto
{
    String usernameFrom;
    String usernameTo;
}
