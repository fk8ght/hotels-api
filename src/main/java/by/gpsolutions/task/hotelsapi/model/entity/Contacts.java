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
public class Contacts {

    @Column(name = "contacts_phone", nullable = false)
    private String phone;

    @Column(name = "contacts_email", nullable = false)
    private String email;
}

