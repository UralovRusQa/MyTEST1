package api.spec.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessReg {

  private Integer id;
  private String token;

  public SuccessReg() {}

  public SuccessReg(Integer id, String token) {
    this.id = id;
    this.token = token;
  }

  // геттеры добавили аннотацией @Getter из библиотеки Ломбок
//  public Integer getId() {
//    return id;
//  }
//
//  public String getToken() {
//    return token;
//  }
}
