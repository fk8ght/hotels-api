package by.gpsolutions.task.hotelsapi.controller;

import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Search", description = "Поиск отелей по параметрам")
public interface SearchApi {

    @Operation(
            summary = "Поиск отелей",
            description = "Параметры: name, brand, city, country, amenities. Amenities можно передавать несколько раз."
    )
    Page<HotelSummaryResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @Parameter(description = "Пример: amenities=Free%20WiFi&amenities=Free%20parking")
            @RequestParam(required = false) List<String> amenities,
            Pageable pageable
    );
}

