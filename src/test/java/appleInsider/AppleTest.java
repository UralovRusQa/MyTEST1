package appleInsider;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class AppleTest extends BaseTest {

  public final static String BASE_URL = "https://appleinsider.ru";
  private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
  private final static String EXPECTED_WORD = "iphone-13";

  @Test
  public void checkHref(){
//    MainPage mainPage = new MainPage(BASE_URL);
//    String href = mainPage.search(SEARCH_STRING).getHrefFromFirstArticle();
//    Assert.assertTrue(href.contains("iphone-13"));  более длинный вариант кода ниже

    // можно весь этот код написать 1 строкой:
    Assert.assertTrue(new MainPage(BASE_URL)
      .search(SEARCH_STRING)
      .getHrefFromFirstArticle()
      .contains(EXPECTED_WORD));
  }

}
