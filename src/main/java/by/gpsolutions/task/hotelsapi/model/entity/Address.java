package by.gpsolutions.task.hotelsapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    @Column(name = "address_house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "address_street", nullable = false)
    private String street;

    @Column(name = "address_city", nullable = false)
    private String city;

    @Column(name = "address_country", nullable = false)
    private String country;

    @Column(name = "address_post_code", nullable = false)
    private String postCode;
}

