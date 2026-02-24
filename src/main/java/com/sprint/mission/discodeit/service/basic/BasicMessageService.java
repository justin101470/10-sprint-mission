package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {

  private final MessageRepository messageRepository;
  private final ChannelRepository channelRepository;
  private final UserRepository userRepository;
  private final BinaryContentRepository binaryContentRepository;

  @Override
  public MessageResponseDto create(MessageCreateDto dto, List<MultipartFile> files) {
    if (!channelRepository.existsById(dto.getChannelId())) {
      throw new NoSuchElementException("채널을 찾을 수 없습니다.");
    }
    if (!userRepository.existsById(dto.getSenderId())) {
      throw new NoSuchElementException("유저를 찾을 수 없습니다.");
    }

    Message message = new Message(dto.getSenderId(), dto.getChannelId(), dto.getContent());
    List<UUID> attachmentIds = new ArrayList<>();

    if (files != null && !files.isEmpty()) {
      for (MultipartFile file : files) {
        try {
          BinaryContent content = new BinaryContent(
              file.getBytes(),
              file.getOriginalFilename(),
              file.getContentType()
          );
          // 파일 저장 및 ID 수집
          binaryContentRepository.save(content);
          attachmentIds.add(content.getId());
        } catch (java.io.IOException e) {
          throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
      }
    }

    message.assignAttachments(attachmentIds);
    messageRepository.save(message);
    return convertToResponseDto(message);
  }

  @Override
  public List<MessageResponseDto> findallByChannelId(UUID channelId) {
    return messageRepository.findAllByChannelId(channelId).stream()
        .map(this::convertToResponseDto)
        .toList();
  }

  @Override
  public MessageResponseDto update(UUID messageId, MessageUpdateDto dto) {
    Message message = messageRepository.findById(messageId)
        .orElseThrow(() -> new NoSuchElementException("메시지를 찾을 수 없습니다."));

    if (dto.getContent() != null && !dto.getContent().isBlank()) {
      message.updateContent(dto.getContent());
      messageRepository.save(message);
    }

    return convertToResponseDto(message);
  }

  @Override
  public void delete(UUID messageId) {
    Message message = messageRepository.findById(messageId)
        .orElseThrow(() -> new NoSuchElementException("메시지를 찾을 수 없습니다."));

    if (message.getAttachmentIds() != null) {
      for (UUID fileId : message.getAttachmentIds()) {
        binaryContentRepository.deleteById(fileId);
      }
    }

    messageRepository.deleteById(messageId);
  }

  private MessageResponseDto convertToResponseDto(Message message) {
    return new MessageResponseDto(
        message.getId(),
        message.getAuthorId(),
        message.getChannelId(),
        message.getContent(),
        message.getCreatedAt(),
        message.getUpdatedAt(),
        message.getAttachmentIds()
    );
  }

  @Override
  public MessageResponseDto find(UUID id) {
    Message message = messageRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("메시지를 찾을 수 없습니다."));
    return convertToResponseDto(message);
  }
}
