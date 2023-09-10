package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> printCityById(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
        Map<String, String> weatherData = weatherService.getWeatherData(city.getName());

        return weatherData;
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> searchByFirstLetters(
            @RequestParam(value = "name", required = false) String name
    ) {
        List<City> founCities = name == null ? cityRepository.findAllByOrderByName()
                : cityRepository.findAllByNameStartsWithIgnoreCase(name);

        List<Map<String, String>> result = new ArrayList<>();

        for (City city : founCities) {
            Map<String, String> currCityMap = new HashMap<>();
            String currName = city.getName();
            Map<String, String> weatherData = weatherService.getWeatherData(currName);
            String temperature = weatherData.get("temperature");

            currCityMap.put("temperature", temperature);
            currCityMap.put("name", currName);

            result.add(currCityMap);

        }

        return result;
    }
    // END
}

