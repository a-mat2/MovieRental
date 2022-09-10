package category;

public class PrintCategoryDto extends CategoryDto {

    public PrintCategoryDto(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "\n" + getId() + " - " + getName();
    }
}
