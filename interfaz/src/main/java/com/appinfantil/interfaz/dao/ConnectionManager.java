package com.appinfantil.interfaz.dao;

import com.appinfantil.interfaz.App;
import com.appinfantil.interfaz.controllers.LoginController;
import com.appinfantil.interfaz.controllers.SignUpController;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ConnectionManager {
    public static SignUpController signUpController;
    public static LoginController loginController;

    public static void createUser() throws IOException {
        User user = new User();
        user.setLoggedIn(true);
        user.setUsername(signUpController.username.getText());
        user.setPassword(signUpController.password.getText());

        if (signUpController.age.getText().equals("")) {
            user.setAge(0);
        } else {
            user.setAge(Integer.parseInt(signUpController.age.getText()));
        }

        user.setPasswordConfirmed(signUpController.confirm.getText());
        List<String> violations = user.validate();
        System.out.println(violations.size());

        if (violations.size() == 0) {

            if (addUser(user)) {
                App.setRoot("login");
                loginController.warning.setTextFill(Color.web("00FF00"));
                loginController.warning.setText("User has been successfully created!");
            } else {
                signUpController.errors.setText("Error! cannot create user");
            }

        } else {
            signUpController.errors.setText(violations.get(0));
        }
    }

    public static boolean addUser(User user) {
        boolean flag = false;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appUsers?user=root&password=root");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users(username, pass, age) VALUES (?, ?, ?);")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getAge());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("ERROR AL CREAR USUARIO");
            e.printStackTrace();
        }
        
        return flag;
    }

    public static boolean loginUser() {
        boolean flag = false;
        User user = new User();
        user.setUsername(loginController.username.getText());
        user.setPassword(loginController.password.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appUsers?user=root&password=root");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users;");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User dbUser = new User();
                dbUser.setUsername(rs.getString("username"));
                dbUser.setPassword(rs.getString("pass"));

                if (dbUser.equals(user)) {
                    flag = true;
                    user.setAge(rs.getInt("age"));
                    user.setPasswordConfirmed(rs.getString("pass"));
                    user.setLoggedIn(true);
                    break;
                } else {
                    user.setAge(4);
                    user.setPasswordConfirmed(user.getPassword());
                    user.setLoggedIn(false);

                    List<String> violations = user.validate();
                    loginController.warning.setTextFill(Color.web("FF0000"));
                    loginController.warning.setText(violations.get(0));
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL INICIAR SESION");
        }

        return flag;
    }
}
