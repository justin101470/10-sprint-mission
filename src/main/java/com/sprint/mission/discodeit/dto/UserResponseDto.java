package com.sprint.mission.discodeit.dto;

import com.sprint.mission.discodeit.entity.User;
import java.time.Instant;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserResponseDto {

  private UUID id;
  private String username;
  private String email;
  private UUID profileId;
  private boolean isOnline;
  private Instant createdAt;
  private Instant updatedAt;

  public UserResponseDto(User user, boolean isOnline) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.profileId = user.getProfileId();
    this.isOnline = isOnline;
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }
}
