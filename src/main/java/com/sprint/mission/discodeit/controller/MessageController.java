package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageResponseDto;
import com.sprint.mission.discodeit.dto.MessageUpdateDto;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping
  public ResponseEntity<MessageResponseDto> create(@RequestBody MessageCreateDto dto) {
    return ResponseEntity.ok(messageService.create(dto));
  }

  @GetMapping
  public ResponseEntity<List<MessageResponseDto>> findallByChannelId(@RequestParam UUID channelId) {
    return ResponseEntity.ok(messageService.findallByChannelId(channelId));
  }

  @PatchMapping
  public ResponseEntity<MessageResponseDto> update(@RequestBody MessageUpdateDto dto) {
    return ResponseEntity.ok(messageService.update(dto));
  }

  @DeleteMapping("/{messageId}")
  public ResponseEntity<Void> delete(@PathVariable UUID messageId) {
    messageService.delete(messageId);
    return ResponseEntity.noContent().build();
  }
}