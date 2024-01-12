package sms;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class SmsTest {

  @Before
  public void setUpExtension(){
    /**
     * устанавливаем расширение для браузера, которое мы заранее засунули в папку Ресурсов в виде .crx
     */
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addExtensions(new File("C:\\Users\\User\\IdeaProjects\\MyTEST\\src\\test\\resources\\modheader.crx"));
    Configuration.browserCapabilities = new DesiredCapabilities();
    Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
  }

  @Test
  public  void checkBalance(){
//    SmsApi smsApi = new SmsApi();
//    smsApi.getAccountBalance();
    Selenide.open("https://www.chitai-gorod.ru/");
  }

}
