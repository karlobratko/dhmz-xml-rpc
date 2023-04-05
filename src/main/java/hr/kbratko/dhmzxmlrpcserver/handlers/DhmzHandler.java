package hr.kbratko.dhmzxmlrpcserver.handlers;

import hr.kbratko.dhmzxmlrpcserver.converter.CityWithWeatherToCityConverter;
import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import hr.kbratko.dhmzxmlrpcserver.repository.CityWithWeatherRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Log
public class DhmzHandler {

  private static final CityWithWeatherRepository cityWithWeatherRepository = new CityWithWeatherRepository();

  private static final CityWithWeatherToCityConverter cityWithWeatherToCityConverter = new CityWithWeatherToCityConverter();

  public List<City> getAllCities() {
    return cityWithWeatherRepository.fetchAll().stream()
      .map(cityWithWeatherToCityConverter::convert)
      .collect(Collectors.toList());
  }

  public CityWithWeather getCityByName(String name) {
    return cityWithWeatherRepository.fetchByName(name)
      .orElse(null);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Methods {

    public static final String getAllCities = "getAllCities";

    public static final String getCityByName = "getCityByName";

  }

}
