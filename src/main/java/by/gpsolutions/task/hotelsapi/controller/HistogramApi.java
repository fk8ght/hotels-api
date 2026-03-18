package by.gpsolutions.task.hotelsapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Histogram", description = "Гистограммы по параметрам")
public interface HistogramApi {

    @Operation(
            summary = "Гистограмма по параметру",
            description = "param: brand, city, country, amenities"
    )
    Map<String, Long> histogram(
            @Parameter(description = "brand|city|country|amenities") @PathVariable String param
    );
}

