package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageResponseDto;
import com.sprint.mission.discodeit.dto.MessageUpdateDto;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MessageResponseDto> create(
      @RequestPart("data") MessageCreateDto dto,
      @RequestPart(value = "files", required = false) List<MultipartFile> files
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(dto, files));
  }

  @PatchMapping("/{messageId}")
  public ResponseEntity<MessageResponseDto> update(
      @PathVariable UUID messageId,
      @RequestBody MessageUpdateDto dto
  ) {
    return ResponseEntity.ok(messageService.update(messageId, dto));
  }

  @GetMapping
  public ResponseEntity<List<MessageResponseDto>> findallByChannelId(@RequestParam UUID channelId) {
    return ResponseEntity.ok(messageService.findallByChannelId(channelId));
  }


  @DeleteMapping("/{messageId}")
  public ResponseEntity<Void> delete(@PathVariable UUID messageId) {
    messageService.delete(messageId);
    return ResponseEntity.noContent().build();
  }
}