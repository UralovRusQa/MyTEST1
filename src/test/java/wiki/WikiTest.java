package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.element;

public class WikiTest extends BaseTest {
  private final static String URL = "https://ru.wikipedia.org/wiki/Java";

  @Test
  public void openAllHrefs() {
    // на странице википедии в разделе "содержание" достаём все элементы и достаём из них параметр href
    Selenide.open(URL);
    ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
    List<String> links = new ArrayList<>();
    //1 метод
//    for (int i = 0; i<hrefs.size(); i++){
//      links.add(hrefs.get(i).getAttribute("href"));
//    }

    // 2 метод через ФорИч
//    for(SelenideElement element : hrefs){
//      links.add(element.getAttribute("href"));
//  }

    // 3 метод через СтримАпи
    hrefs.forEach(x->links.add(x.getAttribute("href")));

    //1 метод взаимиодействия: открытие всех ссылок через СтримАпи
    links.forEach(Selenide::open);
//    links.forEach(x->Selenide.open(x));  эти 2 метода одинаковые

    //2 метод взаимодействия: открытие ссылок и ассерты(проверки) Сравним ссылку из браузера и сравним её со ссылкой из коллекции
    for(int i = 0; i<links.size(); i++){
      String listUrl = links.get(i);
      Selenide.open(listUrl); // тут мы получаем ссылку из конкретного элемента списка
      String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
      // тут мы получаем ссылку именно из браузера
      Assert.assertEquals(currentUrl, listUrl);
    }

    // тут рассмотрим получение случайного значения из списка
    while(links.size() > 0){
      int randomNumber = new Random().nextInt(links.size()); //в скобках указываем предел рандомайзера
      Selenide.open(links.get(randomNumber));
      links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
      // чтобы цикл не был бесконечным, уже открытую ссылку удаляем из списка
    }

    // Тут сделаем отдельный список с длинной всех ссылок (то есть хранится будут не ссылки, а их длина в символах
    // Метод map() в СтримАпи является промеждуточным и конвертирует 1 вид данных в другой
    List<Integer> linksLength = hrefs.stream().map(x-> x.getAttribute("href").length()).collect(Collectors.toList());





  }
}
