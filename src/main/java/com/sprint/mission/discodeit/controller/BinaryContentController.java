package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.BinaryContentResponseDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.service.BinaryContentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/binary-contents")
@RequiredArgsConstructor
public class BinaryContentController {

  private final BinaryContentService binaryContentService;

  @GetMapping("/{id}")
  public ResponseEntity<BinaryContentResponseDto> find(@PathVariable UUID id) {
    return ResponseEntity.ok(binaryContentService.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<BinaryContentResponseDto>> findAllByIds(
      @RequestParam(name = "ids") List<UUID> ids
  ) {
    if (ids == null || ids.isEmpty()) {
      return ResponseEntity.ok(List.of());
    }
    return ResponseEntity.ok(binaryContentService.findAllByIds(ids));
  }
}