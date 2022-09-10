package director;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class DirectorService {
    private DirectorDao directorDao;

    public boolean checkIfDirectorExist(DirectorDto directorDto) {
        List<Director> allDirectors = directorDao.getDirectorList();
        Optional<Director> optionalDirector = allDirectors.stream()
                .filter(d -> (d.getFirstName().equalsIgnoreCase(directorDto.getFirstName())
                        && d.getLastName().equalsIgnoreCase(directorDto.getLastName()))).findAny();
        return optionalDirector.isPresent();
    }

    public void addNewDirector(DirectorDto directorDto) {
        Director director = new Director(directorDto.getFirstName(), directorDto.getLastName());
        directorDao.insertNewDirector(director);
    }

    public int getDirectorIdByName(DirectorDto directorDto) {
        List<Director> allDirectors = directorDao.getDirectorList();
        Optional<Director> optionalDirector = allDirectors.stream()
                .filter(d -> (d.getFirstName().equalsIgnoreCase(directorDto.getFirstName())
                        && d.getLastName().equalsIgnoreCase(directorDto.getLastName()))).findAny();
        return optionalDirector.get().getId();
    }
}
