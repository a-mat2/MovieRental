package user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrintUserDto extends UserDto {

    public PrintUserDto(int id, String nickName) {
        super(id, nickName);
    }

    @Override
    public String toString() {
        return getId() + " - " + getNickName();
    }
}
