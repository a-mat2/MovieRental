package movie;

public class PrintMovieDto extends MovieDto {

    public PrintMovieDto(int id, String title, int category, int director, int amount) {
        super(id, title, category, director, amount);
    }

    @Override
    public String toString() {
        return "\n" + getId() + " - " + getTitle();
    }

}
