import category.CategoryDao;
import category.CategoryService;
import config.ConnectionManager;
import director.DirectorDao;
import director.DirectorDto;
import director.DirectorService;
import jakarta.persistence.EntityManager;
import movie.MovieDao;
import movie.MovieDto;
import movie.MovieService;
import movie.PrintMovieDto;
import rent.PrintRentDto;
import rent.RentDao;
import rent.RentDto;
import rent.RentService;
import user.UserDao;
import user.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.close(); // to otwarcie i zamkniecie entityManagera chowa nam caly czerwony log

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner in = new Scanner(System.in);

        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);

        MovieDao movieDao = new MovieDao();
        MovieService movieService = new MovieService(movieDao);

        RentDao rentDao = new RentDao();
        RentService rentService = new RentService(rentDao);

        DirectorDao directorDao = new DirectorDao();
        DirectorService directorService = new DirectorService(directorDao);

        CategoryDao categoryDao = new CategoryDao();
        CategoryService categoryService = new CategoryService(categoryDao);


        while (true) {
            System.out.println("Wybierz uzytkownika:");
            userService.getUsersList().forEach(System.out::println);
            int userId = in.nextInt();
            while (userId != 0) {
                System.out.println("Podaj operacje do wykonania:");
                System.out.println("1 - Wyswietl wszystkie filmy");
                System.out.println("2 - Wyswietl aktualnie dostepne filmy");
                System.out.println("3 - Wyswietl informacje o filmie");
                System.out.println("4 - Wypozycz film");
                System.out.println("5 - Zwroc film");
                System.out.println("6 - Sprawdz wypozyczone filmy");
                System.out.println("7 - Sprawdz dostepnosc filmu");
                if (userService.getUserPrivileges(userId) == 2) {
                    System.out.println("[ADMIN] 8 - Dodaj nowy film do wypozyczenia");
                    System.out.println("[ADMIN] 9 - Zwieksz ilosc posiadanych filmow");
                }
                System.out.println("0 - Wyloguj");

                int selectedOperation = in.nextInt();
                in.nextLine();

                switch (selectedOperation) {
                    case 1 -> {
                        System.out.println("Wszystkie filmy:");
                        List<PrintMovieDto> allMovies = movieService.getListOfAllMovies();
//                        System.out.println(gson.toJson(allMovies));
                        System.out.println(allMovies);
                        System.out.println();
                    }
                    case 2 -> {
                        System.out.println("Aktualnie dostepne filmy:");
                        List<PrintMovieDto> availableMovies = movieService.getListOfAvailableMovies();
//                        System.out.println(gson.toJson(allMovies));
                        System.out.println(availableMovies);
                        System.out.println();
                    }
                    case 3 -> {
                        System.out.println("Podaj ID filmu: ");
                        int movieId = in.nextInt();
                        movieService.displayMovieInfo(movieId);
                        System.out.println();
                    }
                    case 4 -> {
                        System.out.println("Podaj ID filmu, ktory chcesz wypozyczyc: ");
                        int movieId = in.nextInt();
                        RentDto rentDto = new RentDto(movieId, userId);
                        if (!movieService.updateMovieAmount(movieId, -1)) {
                            System.out.println("Niestety ten film nie jest dostepny.");
                            continue;
                        }
                        rentService.rentMovie(rentDto);
                        System.out.println("Pomyslnie wypozyczono film: " + movieService.getTitleById(movieId));
                        System.out.println();
                    }
                    case 5 -> {
                        System.out.println("Podaj ID filmu, ktory chcesz zwrocic: ");
                        System.out.println(rentService.displayUserRents(userId));
                        int movieId = in.nextInt();
                        RentDto rentDto = new RentDto(movieId, userId);
                        Optional<PrintRentDto> isBookRented = rentService.displayUserRents(userId).stream().filter(p -> p.getMovie() == movieId).findAny();
                        if (isBookRented.isEmpty()) {
                            System.out.println("Film o tym ID nie zostal znaleziony na liscie wypozyczonych filmow.");
                            continue;
                        }
                        rentService.returnMovie(rentDto);
                        movieService.updateMovieAmount(userId, 1);
                    }
                    case 6 -> {
                        System.out.println("Wypozyczone filmy:");
                        System.out.println(rentService.displayUserRents(userId));
                    }
                    case 7 -> {
                        System.out.println("Podaj ID filmu: ");
                        int movieId = in.nextInt();
                        String availability = movieService.isMovieAvailable(movieId);
                        System.out.println(availability);
                        System.out.println();
                    }
                    case 8 -> {
                        if (userId < 2) {
                            continue;
                        }
                        System.out.println("Podaj tytul: ");
                        String movieTitle = in.nextLine();
                        System.out.println("Podaj kategorie: ");
                        categoryService.displayAllCategories();
                        int categoryId = in.nextInt();
                        in.nextLine();
                        if (categoryId < 1 || categoryId > 9) {
                            System.out.println("Niepoprawny numer kategorii.");
                            continue;
                        }
                        System.out.println("Podaj imie rezysera: ");
                        String firstName = in.nextLine();
                        System.out.println("Podaj nazwisko rezysera: ");
                        String secondName = in.nextLine();
                        DirectorDto directorDto = new DirectorDto(firstName, secondName);
                        if (!directorService.checkIfDirectorExist(directorDto)) {
                            directorService.addNewDirector(directorDto);
                        }
                        int directorId = directorService.getDirectorIdByName(directorDto);
                        System.out.println("Podaj date premiery [yyyy-mm-dd]: ");
                        String enteredDate = in.nextLine();
                        if (!enteredDate.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                            System.out.println("Niepoprawny format daty [yyyy-mm-dd].");
                            continue;
                        }
                        LocalDate releaseDate = LocalDate.parse(enteredDate);
                        System.out.println("Podaj ilosc dostepnych egzemplarzy filmu: ");
                        int amount = in.nextInt();
                        in.nextLine();
                        MovieDto movieDto = new MovieDto(movieTitle, categoryId, directorId, releaseDate, amount);
                        if (movieService.checkIfMovieExist(movieDto)) {
                            System.out.println("Taki film juz znajduje sie w bazie danych.");
                        } else {
                            movieService.addNewMovie(movieDto);
                            System.out.println("Film zostal dodany.");
                        }
                    }
                    case 9 -> {
                        if (userId < 2) {
                            continue;
                        }
                        System.out.println("Podaj ID filmu: ");
                        System.out.println(movieService.getListOfAllMovies());
                        int movieId = in.nextInt();
                        System.out.println("Ile sztuk chcesz dodac: ");
                        int amountToBeAdded = in.nextInt();
                        movieService.updateMovieAmount(movieId, amountToBeAdded);
                        System.out.println("Dodano " + amountToBeAdded + " sztuki filmu " + movieService.getTitleById(movieId) + ".");
                    }
                    case 0 -> userId = 0;
                }

            }
        }

    }
}