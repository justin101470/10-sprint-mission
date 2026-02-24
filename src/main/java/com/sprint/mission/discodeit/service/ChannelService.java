package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.*;
import java.util.List;
import java.util.UUID;

public interface ChannelService {

  ChannelResponseDto createPublicChannel(ChannelCreateDto dto);

  ChannelResponseDto createPrivateChannel(PrivateChannelCreateDto dto);

  ChannelResponseDto find(UUID channelId);

  List<ChannelResponseDto> findAllByUserId(UUID userId);

  ChannelResponseDto update(UUID channelId, ChannelUpdateDto dto);

  void delete(UUID channelId);

  ChannelResponseDto create(ChannelCreateDto dto);

  List<ChannelResponseDto> findAll();
}