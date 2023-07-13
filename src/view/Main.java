package view;

import controller.UserController;
import model.Category;
import model.Product;
import model.RoleName;
import model.User;
import util.DataBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Set<RoleName> roleadmin = new HashSet<>();
        roleadmin.add(RoleName.ADMIN);
        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(roleadmin);
        userController.save(admin);

        List<Product> list = new ArrayList<>();
        Product p1 = new Product(1,"Iphone 8               ",200,128,100,true, new Category(1, "Iphone"));
        Product p2 = new Product(2,"Iphone XS              ",250,256,100,true,new Category(1, "Iphone"));
        Product p3 = new Product(3,"Iphone 11 Pro Max      ",400,256,100,true,new Category(1, "Iphone"));
        Product p4 = new Product(4,"Iphone 14 Pro Max      ",700,512,100,true,new Category(1, "Iphone"));
        Product p5 = new Product(5,"SamSung Galaxy Note 10",300,32,100,true,new Category(2, "Samsung"));
        Product p6 = new Product(6,"Xiaomi Redmi Note 12   ",350,64,100,true,new Category(3, "Xiaomi"));
        Product p7 = new Product(7,"Oppo Reno 5              ",370,32,100,true, new Category(4, "Oppo"));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        new DataBase<Product>().writeToFile(list,"D:\\project-module3\\src\\util\\products.txt");

        List<Category> list1 = new ArrayList<>();
        Category c1 = new Category(1, "Iphone");
        Category c2 = new Category(2, "Samsung");
        Category c3 = new Category(3, "Xiaomi");
        Category c4 = new Category(4, "Oppo");
        list1.add(c1);
        list1.add(c2);
        list1.add(c3);
        list1.add(c4);
        new DataBase<Category>().writeToFile(list1,"D:\\project-module3\\src\\util\\category.txt");
    }
}
