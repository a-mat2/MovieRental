package rent;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class RentDto {
    private int movie;
    private int user;

    public RentDto(int movie, int user) {
        this.movie = movie;
        this.user = user;
    }

}
