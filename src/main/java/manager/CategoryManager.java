package manager;

import db.DBConnection;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CategoryManager {
    private final Connection connection = DBConnection.getINSTANCE().getConnection();
    private Scanner scanner;

    CategoryManager(Scanner scanner) {
        this.scanner = scanner;
    }

    CategoryManager() {
    }

    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO category (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void editCategoryByID(int id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        try (PreparedStatement preparedStatement = connection.prepareStatement("Update category SET  name = ? where id = ?")) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteCategoryByID(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete FROM category where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAllCategory() {
        List<Category> allCategory = getAllCategory();
        for (Category category : allCategory) {
            System.out.println(category);
        }
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public Category getByID(int id) {
        Category category = new Category();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }


}
