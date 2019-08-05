package com.schibsted.spain.friends.user.query;

import com.schibsted.spain.friends.user.dto.FriendDto;
import java.util.List;

public interface QueryUserHandler
{
    List<FriendDto> getFriends(String username);
}
