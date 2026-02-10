package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class JCFUserService implements UserService {
    private final Map<UUID, User> data = new HashMap<>();

    private UserResponseDto convertToDto(User user) {
        return new UserResponseDto(user, false);
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        try {
            User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());

            data.put(user.getId(), user);

            return convertToDto(user);

        } catch (Exception e) {
            System.err.println("=== 긴급: 유저 생성 중 에러 발생! ===");
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public UserResponseDto find(UUID userId) {
        User user = data.get(userId);
        if (user == null) throw new NoSuchElementException("사용자 없음");
        return convertToDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return data.values().stream().map(this::convertToDto).toList();
    }

    @Override
    public UserResponseDto update(UUID userId, UserUpdateDto dto) {
        User user = data.get(userId);
        if (user == null) throw new NoSuchElementException("사용자 없음");
        user.update(dto.getNickname(), null, dto.getPassword());
        return convertToDto(user);
    }

    @Override
    public void delete(UUID userId) {
        data.remove(userId);
    }

    @Override
    public void updateStatus(UserStatusUpdateDto dto) {
    }
}