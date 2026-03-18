package by.gpsolutions.task.hotelsapi.model.dto;

import lombok.Builder;

@Builder
public record HotelSummaryResponse(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}

