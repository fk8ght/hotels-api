package by.gpsolutions.task.hotelsapi.exception.handler;

import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record ApiError(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}

