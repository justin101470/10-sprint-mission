package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.UserCreateDto;
import com.sprint.mission.discodeit.dto.UserResponseDto;
import com.sprint.mission.discodeit.dto.UserStatusUpdateDto;
import com.sprint.mission.discodeit.dto.UserUpdateDto;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //사용자 등록
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    //사용자 정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> update(
            @RequestParam UUID id,
            @RequestBody UserUpdateDto dto
    ) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    //사용자 정보 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    //모든 사용자 조회
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 사용자 온라인 상태 업데이트
    @RequestMapping(value = "/status/update", method = RequestMethod.POST)
    public ResponseEntity<Void> updateStatus(@RequestBody UserStatusUpdateDto dto) {
        userService.updateStatus(dto);
        return ResponseEntity.ok().build();
    }
}
