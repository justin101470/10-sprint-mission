package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileChannelService implements ChannelService {
    private final String FILE_PATH = "channels.dat";

    @SuppressWarnings("unchecked")
    private List<Channel> loadChannels() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Channel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveChannels(List<Channel> channels) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(channels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ChannelResponseDto convertToDto(Channel c) {
        return new ChannelResponseDto(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getType(),
                c.getUpdatedAt(),
                Collections.emptyList()
        );
    }

    @Override
    public ChannelResponseDto createPublicChannel(ChannelCreateDto dto) {
        List<Channel> channels = loadChannels();
        Channel channel = new Channel(ChannelType.PUBLIC, dto.getName(), dto.getDescription());
        channels.add(channel);
        saveChannels(channels);
        return convertToDto(channel);
    }

    @Override
    public ChannelResponseDto createPrivateChannel(PrivateChannelCreateDto dto) {
        List<Channel> channels = loadChannels();
        Channel channel = new Channel(ChannelType.PRIVATE, "Private Channel", "");
        channels.add(channel);
        saveChannels(channels);
        return convertToDto(channel);
    }

    @Override
    public ChannelResponseDto find(UUID channelId) {
        Channel channel = loadChannels().stream()
                .filter(c -> c.getId().equals(channelId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널을 찾을 수 없습니다."));
        return convertToDto(channel);
    }

    @Override
    public List<ChannelResponseDto> findAllByUserId(UUID userId) {
        return loadChannels().stream().map(this::convertToDto).toList();
    }

    @Override
    public ChannelResponseDto update(ChannelUpdateDto dto) {
        List<Channel> channels = loadChannels();
        for (Channel channel : channels) {
            if (channel.getId().equals(dto.getChannelId())) {
                channel.update(dto.getName(), dto.getDescription());
                saveChannels(channels);
                return convertToDto(channel);
            }
        }
        throw new NoSuchElementException("채널 없음");
    }

    @Override
    public void delete(UUID channelId) {
        List<Channel> channels = loadChannels();
        channels.removeIf(c -> c.getId().equals(channelId));
        saveChannels(channels);
    }

    @Override
    public ChannelResponseDto create(ChannelCreateDto dto) {
        return createPublicChannel(dto);
    }

    @Override
    public List<ChannelResponseDto> findAll() {
        return loadChannels().stream().map(this::convertToDto).toList();
    }
}