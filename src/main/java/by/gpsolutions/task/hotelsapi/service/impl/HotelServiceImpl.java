package by.gpsolutions.task.hotelsapi.service.impl;

import by.gpsolutions.task.hotelsapi.exception.BadRequestException;
import by.gpsolutions.task.hotelsapi.exception.NotFoundException;
import by.gpsolutions.task.hotelsapi.model.dto.CreateHotelRequest;
import by.gpsolutions.task.hotelsapi.model.dto.HotelDetailsResponse;
import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import by.gpsolutions.task.hotelsapi.model.entity.Amenity;
import by.gpsolutions.task.hotelsapi.model.entity.Hotel;
import by.gpsolutions.task.hotelsapi.model.mapper.HotelMapper;
import by.gpsolutions.task.hotelsapi.repository.AmenityRepository;
import by.gpsolutions.task.hotelsapi.repository.HotelRepository;
import by.gpsolutions.task.hotelsapi.service.HotelService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final HotelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<HotelSummaryResponse> getHotels() {
        return hotelRepository.findAll().stream().map(mapper::toSummary).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDetailsResponse getHotel(long id){
        var hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException("Hotel not found: " + id));
        return mapper.toDetails(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HotelSummaryResponse> search(String name, String brand, String city, String country, List<String> amenities, Pageable pageable) {
        return hotelRepository.search(blankToNull(name), blankToNull(brand), blankToNull(city), blankToNull(country), amenities, pageable).map(mapper::toSummary);
    }

    @Override
    @Transactional
    public HotelSummaryResponse create(CreateHotelRequest request) {
        var hotel = mapper.toEntity(request);
        var saved = hotelRepository.save(hotel);
        return mapper.toSummary(saved);
    }

    @Override
    @Transactional
    public void addAmenities(long hotelId, List<String> amenities) {
        if (amenities == null || amenities.isEmpty()) {
            throw new BadRequestException("amenities list must be provided");
        }

        var hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NotFoundException("Hotel not found"));

        Map<String, Amenity> existingByLower = amenityRepository.findByNameIn(amenities).stream()
                .collect(Collectors.toMap(a -> a.getName().toLowerCase(), a -> a, (a, b) -> a));

        for (String aName : amenities) {
            var key = aName.toLowerCase();
            Amenity amenity = existingByLower.get(key);
            if (amenity == null) {
                amenity = amenityRepository.save(Amenity.builder().name(aName).build());
                existingByLower.put(key, amenity);
            }
            hotel.getAmenities().add(amenity);
        }

        hotelRepository.save(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> histogram(String param) {
        String p = blankToNull(param);
        List<Object[]> rows = switch (p) {
            case "brand" -> hotelRepository.countByBrand();
            case "city" -> hotelRepository.countByCity();
            case "country" -> hotelRepository.countByCountry();
            case "amenities" -> hotelRepository.countByAmenity();
            default -> throw new BadRequestException("Unsupported histogram param: " + param);
        };
        Map<String, Long> out = new LinkedHashMap<>();
        for (Object[] r : rows) {
            out.put(String.valueOf(r[0]), ((Number) r[1]).longValue());
        }
        return out;
    }

    private static String blankToNull(String s) {
        if (s == null) {
            return null;
        }
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}

