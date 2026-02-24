package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<UserResponseDto> create(
      @RequestPart("dto") UserCreateDto dto,
      @RequestPart(value = "profileImage", required = false) MultipartFile profileImage // 파일 데이터
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto, profileImage));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @PatchMapping(value = "/{userId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<UserResponseDto> update(
      @PathVariable UUID userId,
      @RequestPart("dto") UserUpdateDto dto,
      @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
  ) {
    return ResponseEntity.ok(userService.update(userId, dto, profileImage));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> delete(@PathVariable UUID userId) {
    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{userId}/userStatus")
  public ResponseEntity<UserResponseDto> updateStatus(@PathVariable UUID userId) {
    return ResponseEntity.ok(userService.updateStatus(userId));
  }
}