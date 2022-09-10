package rent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "rents")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int movie;
    private int user;
    @Column(name = "rent_date")
    private LocalDate rentDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public Rent(int movie, int user) {
        this.movie = movie;
        this.user = user;
        this.rentDate = LocalDate.now();
    }
}
