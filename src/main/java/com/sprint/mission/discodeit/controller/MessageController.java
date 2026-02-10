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
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    //메세지 전송
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<MessageResponseDto> create(@RequestBody MessageCreateDto dto) {
        return ResponseEntity.ok(messageService.create(dto));
    }

    //메세지 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<MessageResponseDto> update(@RequestBody MessageUpdateDto dto) {
        return ResponseEntity.ok(messageService.update(dto));
    }

    //메세지 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }

    //특정 채널의 메시지 목록을 조회할 수 있다.
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<MessageResponseDto>> findAllByChannelId(@RequestParam UUID channelId) {
        return ResponseEntity.ok(messageService.findallByChannelId(channelId));
    }
}
