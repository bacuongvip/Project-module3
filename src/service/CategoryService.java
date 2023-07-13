package service;

import model.Category;
import model.Product;
import model.User;
import util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IGenericService<Category, Integer> {
    private List<Category> categories;
    private DataBase<Category> categoryData = new DataBase();
    public CategoryService() {
        List<Category> list = categoryData.readFromFile(DataBase.CATEGORY_PATH); // Doc du lieu tu file
        if(list == null) {
            list = new ArrayList<>();
        }
        this.categories = list;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public void save(Category category) {
        if(findbyId(category.getId()) == null) {
            categories.add(category);
        } else {
            categories.set(categories.indexOf(findbyId(category.getId())), category);
        }
        categoryData.writeToFile(categories, DataBase.CATEGORY_PATH);
    }

    @Override
    public void delete(Integer id) {
        categories.remove(findbyId(id));
        categoryData.writeToFile(categories, DataBase.CATEGORY_PATH);
    }

    @Override
    public Category findbyId(Integer id) {
        for (Category category : categories) {
            if (category.getId() == id){
                return category;
            }
        }
        return null;
    }
    public int getNewId(){
        int max = 0;
        for (Category category : categories) {
            if (category.getId() > max) {
                max = category.getId();
            }
        }
        return max+1;
    }
}
