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
@XmlRootElement(name = City.Constants.xmlRootElementName)
@XmlType(propOrder = {City.Fields.name, City.Fields.latitude, City.Fields.longitude})
@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Serializable {

  @XmlElement(name = Constants.nameXmlElementName, required = true)
  @EqualsAndHashCode.Include
  protected String name;

  @XmlElement(name = Constants.latitudeXmlElementName, required = true)
  protected BigDecimal latitude;

  @XmlElement(name = Constants.longitudeXmlElementName, required = true)
  protected BigDecimal longitude;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlRootElementName = "Grad";

    public static final String nameXmlElementName = "GradIme";

    public static final String latitudeXmlElementName = "Lat";

    public static final String longitudeXmlElementName = "Lon";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String name = "name";

    public static final String latitude = "latitude";

    public static final String longitude = "longitude";

  }

}
