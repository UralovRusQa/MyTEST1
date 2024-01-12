package api.reqres.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTimeResponse extends UserTime {
  private String updatedAt;

  public UserTimeResponse(String name, String job, String updatedAt) {
    super(name, job);
    this.updatedAt = updatedAt;
  }

}
