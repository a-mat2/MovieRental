package movie;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
public class MovieService {
    MovieDao movieDao;

    public List<PrintMovieDto> getListOfAllMovies() {
        List<Movie> allMovies = movieDao.getListOfAllMovies();
        return allMovies.stream().map(m -> new PrintMovieDto(m.getId(), m.getTitle(), m.getCategory(), m.getDirector(),
                m.getAmount())).collect(Collectors.toList());
    }

    public List<PrintMovieDto> getListOfAvailableMovies() {
        List<Movie> availableMovies = movieDao.getListOfAvailableMovies();
        return availableMovies.stream().map(m -> new PrintMovieDto(m.getId(), m.getTitle(), m.getCategory(), m.getDirector(),
                m.getAmount())).collect(Collectors.toList());
    }

    public void displayMovieInfo(int movieId) {
        Movie movie = movieDao.getMovieById(movieId);
        if (movie != null) System.out.println(movie);
    }

    public String getTitleById(int movieId) {
        String title = movieDao.getTitleById(movieId);
        return title;
    }

    public String isMovieAvailable(int movieId) {
        boolean availability = movieDao.getMovieAvailability(movieId);
        if (movieDao.getMovieById(movieId) != null) {
            return availability ? "Film " + movieDao.getMovieById(movieId).getTitle() + " jest dostepny."
                    : "Film " + movieDao.getMovieById(movieId).getTitle() + " nie jest dostepny.";
        } else return "";
    }

    public boolean updateMovieAmount(int movieId, int amountToBeUpdated) {
        return movieDao.updateMovieAmountById(movieId, amountToBeUpdated);
    }

    public boolean checkIfMovieExist(MovieDto movieDto) {
        List<Movie> allMovies = movieDao.getListOfAllMovies();
        Optional<Movie> optionalMovie = allMovies.stream().filter(m -> (m.getTitle().equalsIgnoreCase(movieDto.getTitle())
                && m.getCategory() == movieDto.getCategory()
                && m.getDirector() == movieDto.getDirector()
                && m.getReleaseDate().equals(movieDto.getReleaseDate()))).findAny();
        return optionalMovie.isPresent();
    }

    public void addNewMovie(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getTitle(), movieDto.getCategory(),
                movieDto.getDirector(), movieDto.getReleaseDate(), movieDto.getAmount());
        movieDao.insert(movie);
    }
}
