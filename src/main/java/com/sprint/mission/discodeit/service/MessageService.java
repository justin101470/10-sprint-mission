package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageResponseDto;
import com.sprint.mission.discodeit.dto.MessageUpdateDto;

import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {

  MessageResponseDto create(MessageCreateDto dto, List<MultipartFile> files);

  MessageResponseDto find(UUID id);

  List<MessageResponseDto> findallByChannelId(UUID channelId);

  MessageResponseDto update(UUID id, MessageUpdateDto dto);

  void delete(UUID id);
}