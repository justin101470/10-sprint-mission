package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.BinaryContentResponseDto;
import com.sprint.mission.discodeit.service.BinaryContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/binary")
@RequiredArgsConstructor
public class BinaryContentController {
    private final BinaryContentService binaryContentService;

    //바이너리 파일 1개 조회
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<BinaryContentResponseDto> find(@RequestParam UUID id) {
        return ResponseEntity.ok(binaryContentService.findById(id));
    }

    //바이너리 파일 여러 개 조회
    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public ResponseEntity<List<BinaryContentResponseDto>> findAll(@RequestParam List<UUID> ids) {
        return ResponseEntity.ok(binaryContentService.findAllByIds(ids));
    }
}
