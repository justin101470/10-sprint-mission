package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/channels")
@RequiredArgsConstructor
public class ChannelController {

  private final ChannelService channelService;

  @PostMapping("/public")
  public ResponseEntity<ChannelResponseDto> createPublic(@RequestBody ChannelCreateDto dto) {
    return ResponseEntity.ok(channelService.createPublicChannel(dto));
  }

  @PostMapping("/private")
  public ResponseEntity<ChannelResponseDto> createPrivate(
      @RequestBody PrivateChannelCreateDto dto) {
    return ResponseEntity.ok(channelService.createPrivateChannel(dto));
  }

  @GetMapping("/{channelId}")
  public ResponseEntity<ChannelResponseDto> find(@PathVariable UUID channelId) {
    return ResponseEntity.ok(channelService.find(channelId));
  }

  @GetMapping
  public ResponseEntity<List<ChannelResponseDto>> findAllByUserId(@RequestParam UUID userId) {
    return ResponseEntity.ok(channelService.findAllByUserId(userId));
  }

  @PatchMapping
  public ResponseEntity<ChannelResponseDto> update(@RequestBody ChannelUpdateDto dto) {
    return ResponseEntity.ok(channelService.update(dto));
  }

  @DeleteMapping("/{channelId}")
  public ResponseEntity<Void> delete(@PathVariable UUID channelId) {
    channelService.delete(channelId);
    return ResponseEntity.noContent().build();
  }
}