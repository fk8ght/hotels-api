package by.gpsolutions.task.hotelsapi.controller;

import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import by.gpsolutions.task.hotelsapi.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchController implements SearchApi {

    private final HotelService hotelService;

    @Override
    @GetMapping("/search")
    public Page<HotelSummaryResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return hotelService.search(name, brand, city, country, amenities, pageable);
    }
}

