package by.gpsolutions.task.hotelsapi.controller;

import by.gpsolutions.task.hotelsapi.model.dto.CreateHotelRequest;
import by.gpsolutions.task.hotelsapi.model.dto.HotelDetailsResponse;
import by.gpsolutions.task.hotelsapi.model.dto.HotelSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Hotels", description = "Создание отелей, получение информации и amenities")
public interface HotelsApi {

    @Operation(summary = "Получение списка всех отелей (кратко)")
    List<HotelSummaryResponse> getHotels();

    @Operation(summary = "Получение расширенной информации по отелю")
    HotelDetailsResponse getHotel(@PathVariable long id);

    @Operation(summary = "Создание нового отеля")
    HotelSummaryResponse createHotel(@RequestBody CreateHotelRequest request);

    @Operation(summary = "Добавление списка amenities к отелю")
    void addAmenities(@PathVariable long id, @RequestBody List<String> amenities);
}

