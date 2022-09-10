package director;

import lombok.Getter;

@Getter
public class DirectorDto {
    private int id;
    private String firstName;
    private String lastName;


    public DirectorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
