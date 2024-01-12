package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

abstract public class BaseTest {

  //класс абстрактный, потому что нам от него нужно ТОЛЬКО наследоваться, без создания экземпляров

  public void setUp() {
    // благодаря библиотеке WebDriverManager, которую мы подключили в ПОМ.хмл, этой строкой мы скачиваем хромдрайвер
    // и указываем все базовые настройки
    WebDriverManager.chromedriver().setup();
    Configuration.browser = "chrome"; // выбираем Configuration именно от библиотеки Selenide
    Configuration.driverManagerEnabled = true;
    Configuration.browserSize = "1920x1080";
    Configuration.headless = false; // отвечает за то, будем ли мы видеть браузер при выполнении автотеста или нет. Нужна для Jenkins
  }

  @Before
  public void init(){
    setUp();
  }

  @After
  public void tearDown(){
    Selenide.closeWebDriver();
  }

}
