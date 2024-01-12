package Yoomoney;

import core.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPageTest extends BaseTest {
  public static final String URL = "https://yoomoney.ru/";

  @FindBy(xpath = "//*[@id='signin']")
  private WebElement loginButton;



}
