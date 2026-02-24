package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.ReadStatusResponseDto;
import com.sprint.mission.discodeit.dto.ReadStatusUpdateDto;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/read-statuses")
@RequiredArgsConstructor
public class ReadStatusController {

  private final ReadStatusService readStatusService;

  @PostMapping
  public ResponseEntity<ReadStatusResponseDto> create(@RequestBody ReadStatusCreateDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(readStatusService.create(dto));
  }

  @PatchMapping("/{readStatusId}")
  public ResponseEntity<ReadStatusResponseDto> update(
      @PathVariable UUID readStatusId,
      @RequestBody ReadStatusUpdateDto dto) {
    return ResponseEntity.ok(readStatusService.update(readStatusId, dto));
  }

  @GetMapping
  public ResponseEntity<List<ReadStatusResponseDto>> findAllByUserId(@RequestParam UUID userId) {
    return ResponseEntity.ok(readStatusService.findAllByUserId(userId));
  }
}