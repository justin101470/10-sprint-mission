package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.BinaryContentResponseDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.service.BinaryContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/binaryContent")
@RequiredArgsConstructor
public class BinaryContentController {
    private final BinaryContentService binaryContentService;

    @GetMapping("/find")
    public ResponseEntity<BinaryContent> find(@RequestParam UUID binaryContentId) {

        BinaryContentResponseDto dto = binaryContentService.findById(binaryContentId);

        BinaryContent binaryContent = new BinaryContent(
                dto.getBytes(),
                dto.getFileName(),
                dto.getContentType()
        );

        return ResponseEntity.ok(binaryContent);
    }
}