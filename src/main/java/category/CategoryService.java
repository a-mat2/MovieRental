package category;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryService {
    private CategoryDao categoryDao;

    public void displayAllCategories(){
        List<Category> allCategories = categoryDao.getListOfAllCategories();
        List<PrintCategoryDto> printCategoryDtoList = allCategories.stream()
                .map(c -> new PrintCategoryDto(c.getId(), c.getName())).collect(Collectors.toList());
        System.out.println(printCategoryDtoList);
    }
}
