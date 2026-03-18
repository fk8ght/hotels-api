package by.gpsolutions.task.hotelsapi.model.mapper;

import by.gpsolutions.task.hotelsapi.model.dto.CreateHotelRequest;
import by.gpsolutions.task.hotelsapi.model.dto.HotelDetailsResponse;
import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import by.gpsolutions.task.hotelsapi.model.entity.Amenity;
import by.gpsolutions.task.hotelsapi.model.entity.Hotel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(CreateHotelRequest req);

    @Mapping(target = "address", source = "address.street")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelSummaryResponse toSummary(Hotel h);

    @Mapping(target = "amenities", source = "amenities")
    HotelDetailsResponse toDetails(Hotel h);

    default List<String> mapAmenitiesToNames(Set<Amenity> amenities) {
        if (amenities == null || amenities.isEmpty()) {
            return List.of();
        }
        List<String> result = new ArrayList<>(amenities.size());
        for (Amenity a : amenities) {
            if (a != null && a.getName() != null) {
                result.add(a.getName());
            }
        }
        return result;
    }
}

