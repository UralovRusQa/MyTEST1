package helpDesk;

import ReadProperties.ConfigProvider;
import core.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {
  @FindBy (xpath = "//select[@id='id_queue']")
  private WebElement queueList;

  @FindBy (xpath = "//select[@id='id_queue']///option[@value='1']")
  private WebElement queueValue;

  @FindBy (id = "id_title")
  private  WebElement title;

  @FindBy (id = "id_body")
  private WebElement body;

  @FindBy (id = "id_due_data")
  private WebElement dataField;

  @FindBy (xpath = "//table[@class='ui-datepicker-calendar']//a[text()='23']")
  private WebElement dataValue;

  @FindBy (id = "id_submitter_email")
  private WebElement email;

  @FindBy (xpath = "//button[@type='submit']")
  private WebElement submitButton;

  @FindBy (id = "uwerDropdown")
  private WebElement loginButton;

  public MainPage() {
    driver.navigate().to(ConfigProvider.URL);
    PageFactory.initElements(driver, this);
  }

  public MainPage createTicket(String titleValue, String bodyValue, String emailValue){
    queueList.click();
    queueValue.click();
    title.sendKeys(titleValue);
    body.sendKeys(bodyValue);
    dataField.click();
    dataValue.click();
    email.sendKeys(emailValue);
    submitButton.click();
    return this;
  }

  public LoginPage openLoginPage(){
    loginButton.click();
    return new LoginPage();
  }


}
