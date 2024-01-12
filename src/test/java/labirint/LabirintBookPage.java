package labirint;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class LabirintBookPage  {
  private final SelenideElement series = $x("//div[@class='series']/a");
  private final SelenideElement weight = $x("//div[@class='weight']");
  private final SelenideElement onSklad = $x("//div[@class='prodtitle-availibility rang-available']/span");

  public static String SERIES = "Серия";
  public static String WEIGHT = "Вес";
  public static String ON_SKLAD = "На складе";

  public LabirintBookPage(String url) {
    Selenide.open(url);
  }

  // тут мы создали хэшпам с Обжектом, потому что значения у нас разные: числа и цифры
  // далее добавляем в него строки со значениями каждого атрибута
  // ниже указан более длинный код метода
//  public Map<String, Object> getAttributes(){
//    Map<String, Object> attributes = new HashMap<>();
//    attributes.put(SERIES, getSeries());
//    attributes.put(WEIGHT, getWeight());
//    attributes.put(ON_SKLAD, isSkladConfirmed());
//    return attributes;
//  }

  public Map<String, Object> getAttributes(){
    return new HashMap<>(){{
      put(SERIES, getSeriesHard());
      put(WEIGHT, getWeight());
      put(ON_SKLAD, isSkladConfirmed());
    }};
  }


  public String getSeries(){
    String seriesValue = series.getText();
    if(seriesValue == "В одном томе"){
      return "В";
    }
    return "H";
  }

  // более простой код метода выше через тернарный оператор
  public String getSeriesHard(){
    return series.getText().equals("В одном томе") ? "В" : "Ж";
  }

  /**
   * поскольку вес на странице книги указан в виде цифр и букв (1106 г)
   * нам нужно оставить только число 1106
   * с помощью метода replaceAll мы заменяем всё кроме цифр на пустое место (\\D+)
   * далее с помощью медота parseInt мы преобразуем строку "1106" в число 1106
   * @return
   */
  public int getWeight(){
    return Integer.parseInt(weight.getText().replaceAll("\\D+", ""));
  }

  public boolean isSkladConfirmed(){
    return onSklad.isDisplayed();
  }

}
