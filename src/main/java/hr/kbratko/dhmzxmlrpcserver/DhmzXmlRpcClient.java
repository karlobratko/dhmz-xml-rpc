package hr.kbratko.dhmzxmlrpcserver;

import hr.kbratko.dhmzxmlrpcserver.handlers.DhmzHandler;
import hr.kbratko.dhmzxmlrpcserver.model.City;
import hr.kbratko.dhmzxmlrpcserver.model.CityWithWeather;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

@Log
public class DhmzXmlRpcClient {

  private final XmlRpcClient xmlRpcClient;

  private DhmzXmlRpcClient(URL host) {
    val config = new XmlRpcClientConfigImpl();

    config.setServerURL(host);
    config.setContentLengthOptional(false);
    config.setEnabledForExtensions(true);

    xmlRpcClient = new XmlRpcClient();
    xmlRpcClient.setConfig(config);
  }

  public static DhmzXmlRpcClient from(URL host) {
    return new DhmzXmlRpcClient(host);
  }

  public static void main(String[] args) throws IOException, XmlRpcException {
    val dhmzXmlRpcClient = DhmzXmlRpcClient.from(new URL(Constants.host));

    dhmzXmlRpcClient.getAllCities();

    dhmzXmlRpcClient.getCityByName("Lastovo");
  }

  public void getAllCities() throws XmlRpcException {
    val params = new Object[]{};
    val response = (Object[]) xmlRpcClient.execute(Handlers.getAllCitiesMethodName, params);
    val cities = Arrays.stream(response)
      .map(obj -> (City) obj)
      .collect(Collectors.toUnmodifiableList());

    cities.forEach(System.out::println);
  }

  public void getCityByName(String name) throws XmlRpcException {
    val params = new Object[]{name};
    val response = xmlRpcClient.execute(Handlers.getCityByNameMethodName, params);
    val cityWithWeather = (CityWithWeather) response;

    System.out.println(cityWithWeather);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String host = "http://localhost:" + DhmzXmlRpcServer.Constants.port;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  private static class Handlers {

    public static final String getAllCitiesMethodName = DhmzXmlRpcServer.Handlers.dhmzHandlerName + "." + DhmzHandler.Methods.getAllCities;

    public static final String getCityByNameMethodName = DhmzXmlRpcServer.Handlers.dhmzHandlerName + "." + DhmzHandler.Methods.getCityByName;

  }

}
