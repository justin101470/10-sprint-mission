package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> data = new HashMap<>();

    private MessageResponseDto convertToDto(Message m) {
        return new MessageResponseDto(
                m.getId(),
                m.getAuthorId(),
                m.getChannelId(),
                m.getContent(),
                m.getCreatedAt(),
                m.getAttachmentIds()
        );
    }

    @Override
    public MessageResponseDto create(MessageCreateDto dto) {
        Message message = new Message(dto.getSenderId(), dto.getChannelId(), dto.getContent());
        data.put(message.getId(), message);
        return convertToDto(message);
    }

    @Override
    public MessageResponseDto find(UUID id) {
        Message message = data.get(id);
        if (message == null) throw new NoSuchElementException("메시지 없음");
        return convertToDto(message);
    }

    @Override
    public List<MessageResponseDto> findallByChannelId(UUID channelId) {
        return data.values().stream()
                .filter(m -> m.getChannelId().equals(channelId))
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public MessageResponseDto update(MessageUpdateDto dto) {
        Message message = data.get(dto.getMessageId());
        if (message == null) throw new NoSuchElementException("메시지 없음");
        message.updateContent(dto.getContent());
        return convertToDto(message);
    }

    @Override
    public void delete(UUID id) {
        data.remove(id);
    }
}