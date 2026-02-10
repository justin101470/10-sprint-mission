package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.ReadStatusResponseDto;
import com.sprint.mission.discodeit.dto.ReadStatusUpdateDto;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/read-status")
@RequiredArgsConstructor
public class ReadStatusController {
    private final ReadStatusService readStatusService;

    //특정 채널의 메시지 수신 정보를 생성할 수 있다.
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ReadStatusResponseDto> create(@RequestBody ReadStatusCreateDto dto) {
        return ResponseEntity.ok(readStatusService.create(dto));
    }
    //특정 채널의 메시지 수신 정보를 수정할 수 있다.
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ReadStatusResponseDto> update(@RequestBody ReadStatusUpdateDto dto) {
        return ResponseEntity.ok(readStatusService.update(dto));
    }
    //특정 사용자의 메시지 수신 정보를 조회할 수 있다.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ReadStatusResponseDto>> findAllByUserId(@RequestParam UUID userId) {
        return ResponseEntity.ok(readStatusService.findAllByUserId(userId));
    }
}
