package helpDesk;

import ReadProperties.ConfigProvider;
import core.BaseSeleniumTest;
import helpers.TestValues;
import org.junit.Assert;
import org.junit.Test;

import static helpers.StringModifier.getUniqueString;

public class HelpDeskTest extends BaseSeleniumTest {
  @Test
  public void checkTicket(){
    String title = getUniqueString(TestValues.TEST_TITLE);
    // в название тикета добавили время, чтобы сделать тайтл уникальным, чтобы при создании нескольких тикетов в тесте,найти нужный

    TicketPage ticketPage = new MainPage().createTicket(title, TestValues.TEST_BODY, TestValues.TEST_EMAIL)
      .openLoginPage()
      .auth(ConfigProvider.DEMO_LOGIN, ConfigProvider.DEMO_PASSWORD)
      .findTicket(title);
    Assert.assertTrue(ticketPage.getTitle().contains(title));
    Assert.assertEquals(ticketPage.getBody(), TestValues.TEST_BODY);
    Assert.assertEquals(ticketPage.getEmail(), TestValues.TEST_EMAIL);
  }
}
