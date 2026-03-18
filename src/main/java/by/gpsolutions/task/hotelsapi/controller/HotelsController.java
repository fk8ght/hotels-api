package by.gpsolutions.task.hotelsapi.controller;

import by.gpsolutions.task.hotelsapi.model.dto.CreateHotelRequest;
import by.gpsolutions.task.hotelsapi.model.dto.HotelDetailsResponse;
import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import by.gpsolutions.task.hotelsapi.service.HotelService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HotelsController implements HotelsApi {

    private final HotelService hotelService;

    @Override
    @GetMapping("/hotels")
    public List<HotelSummaryResponse> getHotels() {
        return hotelService.getHotels();
    }

    @Override
    @GetMapping("/hotels/{id}")
    public HotelDetailsResponse getHotel(@PathVariable long id) {
        return hotelService.getHotel(id);
    }

    @Override
    @PostMapping("/hotels")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelSummaryResponse createHotel(@Valid @RequestBody CreateHotelRequest request) {
        return hotelService.create(request);
    }

    @Override
    @PostMapping("/hotels/{id}/amenities")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAmenities(@PathVariable long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }
}

