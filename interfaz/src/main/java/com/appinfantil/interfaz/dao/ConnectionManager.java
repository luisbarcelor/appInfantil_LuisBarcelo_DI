package com.appinfantil.interfaz.dao;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.controllers.SignUpController;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ConnectionManager {
    public static void createUser(SignUpController controller) throws IOException {
        User user = new User();
        user.setLoggedIn(true);
        user.setUsername(controller.username.getText());
        user.setPassword(controller.password.getText());

        if (controller.age.getText().equals("")) {
            user.setAge(0);
        } else {
            user.setAge(Integer.parseInt(controller.age.getText()));
        }

        user.setPasswordConfirmed(controller.confirm.getText());
        List<String> violations = user.validate();
        System.out.println(violations.size());

        if (violations.size() == 0) {
            //addUser(user);
            App.setRoot("login");
        } else {
            controller.errors.setText(violations.get(0));
        }
    }

    public static void addUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql//localhost:3306/appUsers?username=root&password=root");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users(username, pass, age) VALUES (?, ?, ?);")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAge());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR USUARIO");
        }

    }

    public static boolean loginUser(User user) {
        boolean isLoggedIn = false;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql//localhost:3306/appUsers?username=root&password=root");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users;");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User dbUser = new User();
                dbUser.setUsername(rs.getString("username"));
                dbUser.setPassword(rs.getString("password"));
                dbUser.setAge(rs.getInt("age"));
                dbUser.setPasswordConfirmed(rs.getString("password"));

                if (dbUser.equals(user)) {
                    isLoggedIn = true;
                    break;
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL INICIAR SESION");
        }

        return isLoggedIn;
    }
}
