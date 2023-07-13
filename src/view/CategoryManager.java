package view;

import config.Constants;
import config.InputMethods;
import controller.CategoryController;
import controller.UserController;
import model.Category;

public class CategoryManager {
    private CategoryController categoryController;
    public CategoryManager(CategoryController categoryController) {
        this.categoryController = categoryController;
        while (true){
            NavBar.menuCategory();
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllCategory();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    NavBar.menuAdmin();
                    break;
                default:
                    System.err.println("Please enter 1 to 5");
            }
        }
    }
    public void showAllCategory(){
        for (Category category : categoryController.findAll()) {
            System.out.println(category);
        }
    }
    public void addCategory(){
        System.out.println("Enter category: ");
        String cate = InputMethods.getString();
        for (Category category : categoryController.findAll()) {
            if (category.getName().equals(cate)){
                System.err.println("Category exists !!!");
                return;
            }
        }
        Category category = new Category();
        category.setId(categoryController.getNewId());
        category.setName(cate);
        categoryController.save(category);
        System.out.println("Add Category Success !!!");
    }
    public void updateCategory(){
        System.out.println("Enter Id Category: ");
        int id = InputMethods.getInteger();
        if(categoryController.findbyId(id) == null){
            System.err.println(Constants.NOT_FOUND);
            return;
        } else {
            System.out.println("Enter category: ");
            String cate = InputMethods.getString();
            for (Category category : categoryController.findAll()) {
                if (category.getName().equals(cate)){
                    System.err.println("Category exists !!!");
                    return;
                }
            }
            categoryController.findbyId(id).setName(cate);
        }
        categoryController.save(categoryController.findbyId(id));
        System.out.println("Update Success !!!");
    }
    public void deleteCategory(){
        System.out.println("Enter Id Category: ");
        int id = InputMethods.getInteger();
        if(categoryController.findbyId(id) == null){
            System.err.println(Constants.NOT_FOUND);
        } else {
            categoryController.delete(id);
            System.out.println("Delete Category Success !!!");
        }
    }
}
