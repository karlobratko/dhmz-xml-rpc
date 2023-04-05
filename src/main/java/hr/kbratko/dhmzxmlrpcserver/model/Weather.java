package hr.kbratko.dhmzxmlrpcserver.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
@XmlRootElement(name = Weather.Constants.xmlRootElementName)
@XmlType(propOrder = {Weather.Fields.temperature, Weather.Fields.moisture, Weather.Fields.pressure, Weather.Fields.windDirection, Weather.Fields.windSpeed, Weather.Fields.time})
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather implements Serializable {

  @XmlElement(name = Constants.temperatureXmlElementName, required = true)
  protected BigDecimal temperature;

  @XmlElement(name = Constants.moistureXmlElementName, required = true)
  protected String moisture;

  @XmlElement(name = Constants.pressureXmlElementName, required = true)
  protected String pressure;

  @XmlElement(name = Constants.windDirectionXmlElementName, required = true)
  protected String windDirection;

  @XmlElement(name = Constants.windSpeedXmlElementName, required = true)
  protected String windSpeed;

  @XmlElement(name = Constants.timeXmlElementName, required = true)
  protected String time;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlRootElementName = "Podatci";

    public static final String temperatureXmlElementName = "Temp";

    public static final String moistureXmlElementName = "Vlaga";

    public static final String pressureXmlElementName = "Tlak";

    public static final String windDirectionXmlElementName = "VjetarSmjer";

    public static final String windSpeedXmlElementName = "VjetarBrzina";

    public static final String timeXmlElementName = "Vrijeme";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String temperature = "temperature";

    public static final String moisture = "moisture";

    public static final String pressure = "pressure";

    public static final String windDirection = "windDirection";

    public static final String windSpeed = "windSpeed";

    public static final String time = "time";

  }


}
