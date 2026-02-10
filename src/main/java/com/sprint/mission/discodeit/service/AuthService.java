package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.LoginRequestDto;
import com.sprint.mission.discodeit.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserResponseDto login(LoginRequestDto dto);
}
