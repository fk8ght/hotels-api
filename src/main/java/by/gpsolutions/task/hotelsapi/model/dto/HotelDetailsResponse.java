package by.gpsolutions.task.hotelsapi.model.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record HotelDetailsResponse(
        Long id,
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactsDto contacts,
        ArrivalTimeDto arrivalTime,
        List<String> amenities
) {
    @Builder
    public record AddressDto(
            Integer houseNumber,
            String street,
            String city,
            String country,
            String postCode
    ) {
    }

    @Builder
    public record ContactsDto(
            String phone,
            String email
    ) {
    }

    @Builder
    public record ArrivalTimeDto(
            String checkIn,
            String checkOut
    ) {
    }
}

