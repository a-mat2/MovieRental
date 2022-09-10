package rent;

import java.util.List;
import java.util.stream.Collectors;

public class RentService {
    private RentDao rentDao;

    public RentService(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    public void rentMovie(RentDto rentDto) {
        Rent rent = new Rent(rentDto.getMovie(), rentDto.getUser());
        rentDao.insertNewRent(rent);
    }

    public List<PrintRentDto> displayUserRents(int userId) {
        List<Rent> userRents = rentDao.getUserRents(userId);
        return userRents.stream().map(r -> new PrintRentDto(r.getMovie(), r.getUser())).collect(Collectors.toList());
    }

    public void returnMovie(RentDto rentDto2) {
        rentDao.returnMovie(rentDto2.getMovie(), rentDto2.getUser());
    }
}
