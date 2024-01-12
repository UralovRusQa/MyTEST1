package api.reqres;

import api.reqres.colors.ColorsData;
import api.spec.registration.Register;
import api.spec.registration.SuccessReg;
import api.spec.registration.UnSuccessReg;
import api.spec.Specifications;
import api.reqres.users.UserData;
import api.reqres.users.UserTime;
import api.reqres.users.UserTimeResponse;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresPojoTest {


  private final static String URL = "https://reqres.in";

  @Test
  public void checkAvatarAndIdTest() {
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    List<UserData> users = given()
      .when()
      .get("/api/reqres/users")
      .then().log().all()
      .extract().body().jsonPath().getList("data", UserData.class);

    // как выглядит код без класса Specifications
//    List<UserData> users = given()
//      .when()
//      .contentType(ContentType.JSON)
//      .get(URL+"/api/users?page=2")
//      .then().log().all()
//      .extract().body().jsonPath().getList("data", UserData.class);

    /**
     * позволяем перебрать список и вызывать методы поочередно. Тут мы ФорИчем перебираем каждую переменную
     * проверяя, что в её аватарке содержится номер ID
     */
    users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

    /**
     * тут мы проверяем что у каждого юзера почта заканчивается на @reqres.in
     * stream().allMatch этим медотом мы выявляем все совпадения
     */
    Assert.assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));

    // Еще один вариант решения (тут только аватарки, но также можно решить вопрос с почтой):
    List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
    List<String> ids = users.stream().map(x-> x.getId().toString()).collect(Collectors.toList());
    for(int i = 0; i<avatars.size(); i++){
      Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
    }
  }

  @Test
  public void successRegTest() {
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    Integer id = 4;
    String token = "QpwL5tke4Pnpja7X4";
    Register user = new Register("eve.holt@reqres.in", "pistol");
    SuccessReg successReg = given()
      .body(user)
      .when()
      .post("/api/register")
      .then().log().all()
      .extract().as(SuccessReg.class);

    Assert.assertNotNull(successReg.getId());
    Assert.assertNotNull(successReg.getToken());

    Assert.assertEquals(id, successReg.getId());
    Assert.assertEquals(token, successReg.getToken());
  }

  @Test
  public void unSuccessRegTest(){
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
    Register user = new Register("sydney@fife", "");
    UnSuccessReg unSuccessReg = given()
      .body(user)
      .post("/api/register")
      .then().log().all()
      .extract().as(UnSuccessReg.class);

    Assert.assertEquals("Missing password", unSuccessReg.getError());
  }

  @Test
  public void sortedYearsTest(){
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    List<ColorsData> colors = given()
      .when()
      .get("/api/unknown")
      .then().log().all()
      .extract().body().jsonPath().getList("data", ColorsData.class);
    List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
    List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
    Assert.assertEquals(sortedYears, years);
  }

  @Test
  public void deleteUserTest () {
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(204));
    given()
      .when()
      .delete("/api/reqres/users/2")
      .then().log().all();
  }

  @Test
  public void timeTest(){
    Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
    UserTime user = new UserTime("morpheus", "zion resident");
    UserTimeResponse response = given()
      .body(user)
      .when()
      .put("/api/reqres/users/2")
      .then().log().all()
      .extract().as(UserTimeResponse.class);
    String regex = "\\..*$"; // этим регулярным выражением (проверять их можно тут https://regex101.com/ мы убираем все символы после точки во времени
    String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
    System.out.println(currentTime);
    Assert.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regex, ""));
    System.out.println(response.getUpdatedAt().replaceAll(regex, ""));
  }
}
