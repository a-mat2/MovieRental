package user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private int id;

    private String nickName;

    private int privileges;

    public UserDto(int id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

}
