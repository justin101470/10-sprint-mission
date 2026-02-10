package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class JCFChannelService implements ChannelService {
    private final Map<UUID, Channel> data = new HashMap<>();

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
        Channel channel = new Channel(ChannelType.PUBLIC, dto.getName(), dto.getDescription());
        data.put(channel.getId(), channel);
        return convertToDto(channel);
    }

    @Override
    public ChannelResponseDto createPrivateChannel(PrivateChannelCreateDto dto) {
        Channel channel = new Channel(ChannelType.PRIVATE, "Private Channel", "");
        data.put(channel.getId(), channel);
        return convertToDto(channel);
    }

    @Override
    public ChannelResponseDto find(UUID channelId) {
        Channel channel = data.get(channelId);
        if (channel == null) throw new NoSuchElementException("채널 없음");
        return convertToDto(channel);
    }

    @Override
    public List<ChannelResponseDto> findAllByUserId(UUID userId) {
        return data.values().stream().map(this::convertToDto).toList();
    }

    @Override
    public ChannelResponseDto update(ChannelUpdateDto dto) {
        Channel channel = data.get(dto.getChannelId());
        if (channel == null) throw new NoSuchElementException("채널 없음");
        channel.update(dto.getName(), dto.getDescription());
        return convertToDto(channel);
    }

    @Override
    public void delete(UUID channelId) {
        data.remove(channelId);
    }

    @Override
    public ChannelResponseDto create(ChannelCreateDto dto) {
        return createPublicChannel(dto);
    }

    @Override
    public List<ChannelResponseDto> findAll() {
        return data.values().stream().map(this::convertToDto).toList();
    }
}