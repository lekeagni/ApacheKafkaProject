package com.example.user_service.model;

import lombok.Builder;

import java.beans.Transient;
import java.time.LocalDateTime;

@Builder
public record ErrorException(
        LocalDateTime localDateTime,
        String message,
        int httpStatus,
        @Transient
        String error
) {
}
