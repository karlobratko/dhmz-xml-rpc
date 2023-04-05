package hr.kbratko.dhmzxmlrpcserver;

import hr.kbratko.dhmzxmlrpcserver.handlers.DhmzHandler;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

@Log
public class DhmzXmlRpcServer {

  private final WebServer webServer;

  private DhmzXmlRpcServer(Integer port) {
    webServer = new WebServer(port);
    log.info(String.format("Server starting on port %d", port));

    val xmlRpcServer = webServer.getXmlRpcServer();
    configureXmlRpcServer((XmlRpcServerConfigImpl) xmlRpcServer.getConfig());
    try {
      xmlRpcServer.setHandlerMapping(propertyHandlerMapping());
    } catch (XmlRpcException e) {
      throw new RuntimeException(e);
    }
    log.info("Server initialized");
  }

  public static DhmzXmlRpcServer from(Integer port) {
    return new DhmzXmlRpcServer(port);
  }

  public static void main(String[] args) throws IOException {
    val dhmzXmlRpcServer = DhmzXmlRpcServer.from(Constants.port);

    dhmzXmlRpcServer.start();
  }

  public void start() throws IOException {
    webServer.start();
    log.info("Server started");
  }

  private void configureXmlRpcServer(XmlRpcServerConfigImpl xmlRpcServerConfig) {
    xmlRpcServerConfig.setContentLengthOptional(false);
    xmlRpcServerConfig.setEnabledForExtensions(true);
  }

  private XmlRpcHandlerMapping propertyHandlerMapping() throws XmlRpcException {
    val propertyHandlerMapping = new PropertyHandlerMapping();
    propertyHandlerMapping.addHandler(Handlers.dhmzHandlerName, Handlers.dhmzHandlerClass);
    return propertyHandlerMapping;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final Integer port = 7270;

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Handlers {

    public static final String dhmzHandlerName = "Dhmz";

    public static final Class<DhmzHandler> dhmzHandlerClass = DhmzHandler.class;

  }

}
