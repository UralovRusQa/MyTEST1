package api.kuCoinApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class StreamApiExample {

  public List<TickerData> getTickers(){
    return given()
      .contentType(ContentType.JSON)
      .when()
      .get("https://api.kucoin.com/api/v1/market/allTickers")
      .then().log().body()
      .extract().jsonPath().getList("data.ticker", TickerData.class);
  }

  @Test
  public void checkCrypto(){
    // отсортировали криптовалюты по USDT в конце их названия
    List<TickerData> usdTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
    Assert.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
  }

  @Test
  public void sortHighToLow(){
    // сортируем валюты от большего к меньшему
    List<TickerData> highToLow = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
      @Override
      public int compare(TickerData o1, TickerData o2) {
        return o2.getChangeRate().compareTo(o1.getChangeRate());
      }
    }).collect(Collectors.toList());

    // берём только топ10 валют из списка
    List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());
    Assert.assertEquals(top10.get(0).getSymbol(), "SCPT-USDT");
  }

  @Test
  public void sortLowToHigh(){
    List<TickerData> lowToHigh = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
      .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());
  }

  @Test
  public void map(){
    Map<String, Float> usd = new HashMap<>();
    List<String> lowerCases = getTickers().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
    getTickers().forEach(x->usd.put(x.getSymbol(), Float.parseFloat(x.getChangeRate())));
    List<TickerShort> shortList = new ArrayList<>();
    getTickers().forEach(x->shortList.add(new TickerShort(x.getSymbol(), Float.parseFloat(x.getChangeRate()))));
  }
}
