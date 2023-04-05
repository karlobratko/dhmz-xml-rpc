package hr.kbratko.dhmzxmlrpcserver.repository;

import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import hr.kbratko.dhmzxmlrpcserver.model.Dhmz;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CityWithWeatherRepository {

  private static final OkHttpClient okHttpClient = new OkHttpClient();

  public Set<CityWithWeather> fetchAll() {
    val request = new Request.Builder()
      .url(Constants.dhmzHostUrl)
      .build();
    val call = okHttpClient.newCall(request);
    try {
      @Cleanup val response = call.execute();
      if (Objects.isNull(response.body()))
        throw new RuntimeException("Response body is empty.");

      val dhmz = (Dhmz) JAXBContext.newInstance(Dhmz.class).createUnmarshaller()
        .unmarshal(response.body().byteStream());

      return Set.copyOf(dhmz.getCities());
    } catch (IOException | JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<CityWithWeather> fetchByName(String name) {
    return fetchAll().stream()
      .filter(cityWithName -> cityWithName.getName().equals(name))
      .findFirst();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String dhmzHostUrl = "https://vrijeme.hr/hrvatska_n.xml";

  }

}
