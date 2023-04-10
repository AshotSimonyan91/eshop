package manager;

import commands_interface.Commands;

import java.util.Scanner;

public class GeneralManager implements Commands {

    private Scanner scanner = new Scanner(System.in);
    private CategoryManager categoryManager = new CategoryManager(scanner);
    private ProductManager productManager = new ProductManager(scanner, categoryManager);
    private boolean isTrue = true;


    public GeneralManager() {
    }

    public void working() {
        while (isTrue) {
            printCommands();
            switch (scanner.nextLine()) {
                case EXIT -> isTrue = false;
                case ADD_CATEGORY -> addCategory();
                case EDIT_CATEGORY_BY_ID -> editCategoryByID();
                case DELETE_CATEGORY_BY_ID -> deleteCategoryByID();
                case ADD_PRODUCT -> addProduct();
                case EDIT_PRODUCT_BY_ID -> editProductByID();
                case DELETE_PRODUCT_BY_ID -> deleteProductByID();
                case PRINT_SUM_OF_PRODUCT -> printSUMOfProduct();
                case PRINT_MAX_OF_PRICE_PRODUCT -> printMAXOfPriceProduct();
                case PRINT_MIN_OF_PRICE_PRODUCT -> printMINOfPriceProduct();
                case PRINT_AVG_OF_PRICE_PRODUCT -> printAVGOfPriceProduct();
            }
        }
    }

    private void printAVGOfPriceProduct() {
        System.out.println(productManager.getProductsAveragePrice());
    }

    private void printMINOfPriceProduct() {
        System.out.println(productManager.getProductsMinimumPrice());
    }

    private void printMAXOfPriceProduct() {
        System.out.println(productManager.getProductsMaximumPrice());
    }

    private void printSUMOfProduct() {
        System.out.println(productManager.getAllProductsCount());
    }

    private void deleteProductByID() {
        productManager.printAllProduct();
        System.out.println("Please input product id");
        int id = Integer.parseInt(scanner.nextLine());
        productManager.deleteProductByID(id);
    }

    private void editProductByID() {
        productManager.printAllProduct();
        System.out.println("Please input category id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input product name");
        String name = scanner.nextLine();
        System.out.println("Please input product description");
        String description = scanner.nextLine();
        System.out.println("Please input product price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please input product quantity");
        int quantity = Integer.parseInt(scanner.nextLine());
        categoryManager.printAllCategory();
        System.out.println("Please input category id");
        int category_id = Integer.parseInt(scanner.nextLine());
        productManager.editProductByID(id, name, description, price, quantity, category_id);
    }

    private void addProduct() {
        productManager.printAllProduct();
        System.out.println("Please input product name");
        String name = scanner.nextLine();
        System.out.println("Please input product description");
        String description = scanner.nextLine();
        System.out.println("Please input product price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please input product quantity");
        int quantity = Integer.parseInt(scanner.nextLine());
        categoryManager.printAllCategory();
        System.out.println("Please input category id");
        int category_id = Integer.parseInt(scanner.nextLine());
        productManager.addProduct(name, description, price, quantity, category_id);
    }

    private void deleteCategoryByID() {
        categoryManager.printAllCategory();
        System.out.println("Please input category id");
        int id = Integer.parseInt(scanner.nextLine());
        categoryManager.deleteCategoryByID(id);
    }

    private void editCategoryByID() {
        categoryManager.printAllCategory();
        System.out.println("Please input category id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Please input category name");
        String name = scanner.nextLine();
        categoryManager.editCategoryByID(id, name);
    }

    private void addCategory() {
        categoryManager.printAllCategory();
        System.out.println("Please input category name");
        String name = scanner.nextLine();
        categoryManager.addCategory(name);
    }

}
