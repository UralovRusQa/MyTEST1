package ReadProperties;

import com.typesafe.config.ConfigFactory;
import core.BaseTest;
import org.junit.Test;

import java.io.IOException;

public class PropertiesTest extends BaseTest {
  @Test
  public void readProperties() throws IOException {
    System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
    String urlFromProperty = System.getProperty("age");
    System.out.println(urlFromProperty);
  }

  @Test
  public void readFromConf(){
    String urlFromConfig = ConfigProvider.URL;
    System.out.println(urlFromConfig);
  }
}
