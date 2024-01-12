package api.spec.registration;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UnSuccessReg {
  private String error;

  public UnSuccessReg() {}

  public UnSuccessReg(String error) {
    this.error = error;
  }

  // геттеры добавили аннотацией @Getter из библиотеки Ломбок, поэтому отдельно объявлять их нет смысла
//  public String getError() {
//    return error;
//  }
}
