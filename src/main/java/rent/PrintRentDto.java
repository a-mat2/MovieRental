package rent;

import movie.MovieDao;
import movie.MovieService;

public class PrintRentDto extends RentDto {
    private MovieDao movieDao = new MovieDao();
    private MovieService movieService = new MovieService(movieDao);

    public PrintRentDto(int movie, int user) {
        super(movie, user);
    }

    @Override
    public String toString() {
        return getMovie() + " - " + movieService.getTitleById(getMovie());
    }
}
