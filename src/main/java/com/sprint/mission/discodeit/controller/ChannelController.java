package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.dto.ChannelCreateDto;
import com.sprint.mission.discodeit.dto.ChannelResponseDto;
import com.sprint.mission.discodeit.dto.ChannelUpdateDto;
import com.sprint.mission.discodeit.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/channel")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    //공개/비공개 채널 생성
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ChannelResponseDto> create(@RequestBody ChannelCreateDto dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }

    // 공개 채널 정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ChannelResponseDto> update(
            @RequestParam UUID id,
            @RequestBody ChannelUpdateDto dto
            ) {
        return ResponseEntity.ok(channelService.update(dto));
    }

    // 채널 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        channelService.delete(id);
        return ResponseEntity.ok().build();
    }

    //특정 사용자가 볼 수 있는 모든 채널 목록을 조회할 수 있다.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ChannelResponseDto>> findAllByMemberId(@RequestParam UUID memberId) {
        return ResponseEntity.ok(channelService.findAllByUserId(memberId));
    }
}
