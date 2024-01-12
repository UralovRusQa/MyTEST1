package sms;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NumberPage {
  private SelenideElement accountBalance = $x("//a[@id='balansUser']");
  private SelenideElement phoneIdText = $x("//div[@id='getNumberTable']//tr[2]//td[1]");
  private SelenideElement phoneNumberText = $x("//div[@class='input=group']//input");

  public String getAccountBalance(){
    return accountBalance.getText();
  }

  public String getPhoneIdText(){
    return phoneIdText.getText();
  }

  public String getPhoneNumberText(){
    return phoneNumberText.getText();
  }
}
