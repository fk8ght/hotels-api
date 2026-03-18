package by.gpsolutions.task.hotelsapi.controller;

import by.gpsolutions.task.hotelsapi.service.HotelService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HistogramController implements HistogramApi {

    private final HotelService hotelService;

    @Override
    @GetMapping("/histogram/{param}")
    public Map<String, Long> histogram(@PathVariable String param) {
        return hotelService.histogram(param);
    }
}

