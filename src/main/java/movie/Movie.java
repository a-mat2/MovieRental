package movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "movies")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int category;
    private int director;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private int amount;

    @Override
    public String toString() {
        return "\n" + getId() + " - " + getTitle() + ", Kategoria: " + getCategory() +
                ", Rezyser: " + getDirector() + ", Data premiery: " + getReleaseDate()
                + ", Ilosc w wypozyczalni: " + getAmount();
    }

    public Movie(String title, int category, int director, LocalDate releaseDate, int amount) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.releaseDate = releaseDate;
        this.amount = amount;
    }
}
