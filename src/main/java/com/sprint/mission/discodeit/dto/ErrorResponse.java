package com.sprint.mission.discodeit.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final String message;
    private final int status;
}