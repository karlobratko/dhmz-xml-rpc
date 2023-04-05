package hr.kbratko.dhmzxmlrpcserver.converter;

import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import lombok.NonNull;
public class CityWithWeatherToCityConverter implements Converter<CityWithWeather, City> {

  @Override
  public City convert(@NonNull CityWithWeather source) {
    return City.builder()
      .name(source.getName())
      .longitude(source.getLongitude())
      .latitude(source.getLatitude())
      .build();
  }

}
