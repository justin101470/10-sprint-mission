package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.dto.LoginRequestDto;
import com.sprint.mission.discodeit.dto.UserResponseDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.AuthService;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class JCFAuthService implements AuthService {
    private final UserService userService;

    public JCFAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserResponseDto login(LoginRequestDto dto) {
        return userService.findAll().stream()
                .filter(u -> u.getEmail().equals(dto.getUsername()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("일치하는 유저가 없습니다."));
    }
}