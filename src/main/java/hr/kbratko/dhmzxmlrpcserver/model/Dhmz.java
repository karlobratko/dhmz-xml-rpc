package hr.kbratko.dhmzxmlrpcserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
@XmlRootElement(name = Dhmz.Constants.xmlRootElementName)
@XmlType(propOrder = {Dhmz.Fields.cities})
@XmlAccessorType(XmlAccessType.FIELD)
public class Dhmz implements Serializable {

  @Singular
  @XmlElement(name = Constants.citiesXmlElementName)
  protected List<CityWithWeather> cities = new ArrayList<>();

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlRootElementName = "Hrvatska";

    public static final String citiesXmlElementName = "Grad";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String cities = "cities";

  }

}
