package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  UserResponseDto create(UserCreateDto dto, MultipartFile profileImage);

  UserResponseDto find(UUID userId);

  List<UserResponseDto> findAll();

  UserResponseDto update(UUID id, UserUpdateDto dto, MultipartFile profileImage);

  void delete(UUID userId);

  UserResponseDto updateStatus(UUID id);
}