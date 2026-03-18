package by.gpsolutions.task.hotelsapi.service;

import by.gpsolutions.task.hotelsapi.model.dto.CreateHotelRequest;
import by.gpsolutions.task.hotelsapi.model.dto.HotelDetailsResponse;
import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
    List<HotelSummaryResponse> getHotels();
    HotelDetailsResponse getHotel(long id);
    Page<HotelSummaryResponse> search(String name, String brand, String city, String country, List<String> amenities, Pageable pageable);
    HotelSummaryResponse create(CreateHotelRequest request);
    void addAmenities(long hotelId, List<String> amenities);
    Map<String, Long> histogram(String param);
}

