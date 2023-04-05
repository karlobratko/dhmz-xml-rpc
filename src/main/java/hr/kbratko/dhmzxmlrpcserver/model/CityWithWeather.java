package hr.kbratko.dhmzxmlrpcserver.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true, callSuper = true)
@XmlRootElement(name = CityWithWeather.Constants.xmlRootElementName)
@XmlType(propOrder = {CityWithWeather.Fields.weather})
@XmlAccessorType(XmlAccessType.FIELD)
public class CityWithWeather extends City implements Serializable {

  @XmlElement(name = Constants.weatherXmlElementName)
  protected Weather weather;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlRootElementName = "Grad";

    public static final String weatherXmlElementName = "Podatci";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String weather = "weather";

  }

}
