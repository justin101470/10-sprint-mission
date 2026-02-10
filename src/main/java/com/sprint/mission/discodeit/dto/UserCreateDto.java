package com.sprint.mission.discodeit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private String username;
    private String email;
    private String password;
    //파라미터 그룹화
    //private BinaryContentDto profileImage;
    @com.fasterxml.jackson.annotation.JsonProperty("profileImage")
    private BinaryContentDto profileImage = null;
}
