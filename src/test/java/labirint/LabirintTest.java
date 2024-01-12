package labirint;

import core.BaseTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LabirintTest extends BaseTest {
  private final static String URL = "https://www.labirint.ru/books/989044/";


  /**
   * тут решаем задачу через хэшкарту
   */
  @Test
  public void checkAttributeHashMap() {
    LabirintBookPage labirintBookPage = new LabirintBookPage(URL);
    // тут мы создаём ХЭШКАРТУ с ОЖИДАЕМЫМИ ЗНАЧЕНИЯМИ!
    Map<String, Object> expectedAttribute = new HashMap<>();
    expectedAttribute.put(LabirintBookPage.SERIES, "В");
    expectedAttribute.put(LabirintBookPage.WEIGHT, 1106);
    expectedAttribute.put(LabirintBookPage.ON_SKLAD, true);

    // тут карта с актуальными значениями на странице
    Map<String, Object> actualAttribute = labirintBookPage.getAttributes();

    Assert.assertEquals(expectedAttribute, actualAttribute);
  }

  /**
   * тут решаем эту же задачу через классы
   */
  @Test
  public void checkAttributeClass(){
    LabirintBookPage labirintBookPage = new LabirintBookPage(URL);
    Book expectedAttributes = new Book("В", 1106, true);
    Book actualAttributes = new Book(labirintBookPage.getSeriesHard(), labirintBookPage.getWeight(),
      labirintBookPage.isSkladConfirmed());

    // сравнивать классы напрямую мы не можем, поэтому выполняем через EqualsBuilder.refclectionEquals(o1,o2))

//    Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedAttributes, actualAttributes));

    Assert.assertEquals(expectedAttributes.getSeries(), actualAttributes.getSeries());
    Assert.assertEquals(expectedAttributes.getWeight(), actualAttributes.getWeight());
    Assert.assertEquals(expectedAttributes.isOnSklad(), actualAttributes.isOnSklad());


  }

}
