package user;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    public List<PrintUserDto> getUsersList() {
            List<User> users = userDao.getAllUsers();
            return users.stream().map(u -> new PrintUserDto(u.getId(), u.getNickName())).collect(Collectors.toList());
    }

    public int getUserPrivileges(int userId) {
        return userDao.getUserPrivileges(userId);
    }
}
