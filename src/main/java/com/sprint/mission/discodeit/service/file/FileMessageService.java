package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileMessageService implements MessageService {
    private final String FILE_PATH = "messages.dat";

    @SuppressWarnings("unchecked")
    private List<Message> loadMessages() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Message>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveMessages(List<Message> messages) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        List<Message> messages = loadMessages();
        Message newMessage = new Message(dto.getSenderId(), dto.getChannelId(), dto.getContent());

        messages.add(newMessage);
        saveMessages(messages);
        return convertToDto(newMessage);
    }

    @Override
    public MessageResponseDto find(UUID id) {
        Message message = loadMessages().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("메시지를 찾을 수 없습니다."));
        return convertToDto(message);
    }

    @Override
    public List<MessageResponseDto> findallByChannelId(UUID channelId) {
        return loadMessages().stream()
                .filter(m -> m.getChannelId().equals(channelId))
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public MessageResponseDto update(MessageUpdateDto dto) {
        List<Message> messages = loadMessages();
        for (Message message : messages) {
            if (message.getId().equals(dto.getMessageId())) {
                message.updateContent(dto.getContent());
                saveMessages(messages);
                return convertToDto(message);
            }
        }
        throw new NoSuchElementException("수정할 메시지가 없습니다.");
    }

    @Override
    public void delete(UUID id) {
        List<Message> messages = loadMessages();
        messages.removeIf(m -> m.getId().equals(id));
        saveMessages(messages);
    }
}