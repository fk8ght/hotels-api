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
public class ArrivalTime {

    @Column(name = "arrival_check_in", nullable = false)
    private String checkIn;

    @Column(name = "arrival_check_out")
    private String checkOut;
}

