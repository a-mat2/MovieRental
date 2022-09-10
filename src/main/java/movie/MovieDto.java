package movie;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class MovieDto {
    private int id;
    private String title;
    private int category;
    private int director;
    private LocalDate releaseDate;
    private int amount;

    public MovieDto(int id, String title, int category, int director, int amount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.director = director;
        this.amount = amount;
    }

    public MovieDto(String title, int category, int director, LocalDate releaseDate, int amount) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.releaseDate = releaseDate;
        this.amount = amount;
    }
}
