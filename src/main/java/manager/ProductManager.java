package manager;

import db.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ProductManager {
    private Connection connection = DBConnection.getINSTANCE().getConnection();
    private Scanner scanner;

    private CategoryManager categoryManager;

    ProductManager(Scanner scanner, CategoryManager categoryManager) {
        this.scanner = scanner;
        this.categoryManager = categoryManager;
    }

    public void addProduct(String name, String description, double price, int quantity, int category_id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Product (name,description,price,quantity,category) " +
                "VALUES (?,?,?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setInt(5, category_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProductByID(int id, String name, String description, double price, int quantity, int category_id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Update Product SET  name = ?, description = ?, " +
                "price = ?, quantity = ?, category = ? where  id = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setInt(5, category_id);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void printAllProduct() {
        List<Product> allProduct = getAllProduct();
        for (Product product : allProduct) {
            System.out.println(product);
        }
    }

    private List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Product")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCategory(categoryManager.getByID(resultSet.getInt("category")));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void deleteProductByID(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete FROM Product where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSumOfProduct() {
        int sum = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(quantity) AS SUM  from Product")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public double getMaxOfPriceProduct() {
        double max = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(price) AS MAX_PRICE  from Product")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                max = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return max;
    }

    public double getMinOfPriceProduce() {
        double min = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT MIN(price) AS MIN_PRICE  from Product")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                min = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return min;
    }

    public double getAVGOfPriceProduct() {
        double avg = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT AVG(price) AS AVG_PRICE  from Product")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                avg = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return avg;
    }
}
