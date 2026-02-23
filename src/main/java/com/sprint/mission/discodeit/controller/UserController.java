package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto dto) {
    // userService.signUp -> create로 변경
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @PatchMapping("/{userId}")
  public ResponseEntity<UserResponseDto> update(
      @PathVariable UUID userId,
      @RequestBody UserUpdateDto dto
  ) {
    // 파라미터 2개(userId, dto)를 넘기도록 수정
    return ResponseEntity.ok(userService.update(userId, dto));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> delete(@PathVariable UUID userId) {
    userService.delete(userId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{userId}/userStatus")
  public ResponseEntity<Void> updateStatus(@RequestBody UserStatusUpdateDto dto) {
    // 서비스 반환 타입 void에 맞춰 수정
    userService.updateStatus(dto);
    return ResponseEntity.ok().build();
  }
}