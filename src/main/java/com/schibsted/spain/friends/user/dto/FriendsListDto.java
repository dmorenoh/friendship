package com.schibsted.spain.friends.user.dto;

import java.util.List;
import lombok.Value;

@Value
public class FriendsListDto
{
    List<FriendDto> friends;
}
