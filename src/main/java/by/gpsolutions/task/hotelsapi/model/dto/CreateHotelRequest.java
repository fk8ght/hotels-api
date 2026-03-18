package by.gpsolutions.task.hotelsapi.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateHotelRequest(
        @NotBlank String name,
        String description,
        @NotBlank String brand,
        @NotNull @Valid AddressDto address,
        @NotNull @Valid ContactsDto contacts,
        @NotNull @Valid ArrivalTimeDto arrivalTime
) {
    public record AddressDto(
            @NotNull @Positive Integer houseNumber,
            @NotBlank String street,
            @NotBlank String city,
            @NotBlank String country,
            @NotBlank String postCode
    ) {
    }

    public record ContactsDto(
            @NotBlank String phone,
            @NotBlank @Email String email
    ) {
    }

    public record ArrivalTimeDto(
            @NotBlank String checkIn,
            String checkOut
    ) {
    }
}

