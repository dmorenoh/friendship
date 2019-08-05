package com.schibsted.spain.friends.user.query;

import com.schibsted.spain.friends.common.data.UsersInMemory;
import com.schibsted.spain.friends.user.dto.FriendDto;
import com.schibsted.spain.friends.user.exception.UserNotFoundException;
import com.schibsted.spain.friends.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultQueryUserHandler implements QueryUserHandler
{

    private final UserRepository userRepository;


    @Override
    public List<FriendDto> getFriends(String username)
    {
        return UsersInMemory.getInstance()
            .getByUsername(username).orElseThrow(() -> new UserNotFoundException("user not found"))
            .getFriends()
            .stream()
            .map(FriendDto::of)
            .collect(Collectors.toList());
    }
}
