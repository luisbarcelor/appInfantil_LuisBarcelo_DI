package com.appinfantil.interfaz.dao;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
    @Size(min = 8, max = 20, message = "Password must be between 8 characters and 20" + "\n" + "characters")
    private String password;

    @AssertTrue(message = "Passwords must match")
    private boolean passwordConfirmed;

    @Min(message = "You must be at least 4 years old", value = 4)
    @Max(message = "Invalid age", value = 120)
    private int age;

    @Size (min = 4, message = "Username must be at least 4 characters long")
    private String username;

    @AssertTrue(message = "Incorrect username or password")
    private boolean isLoggedIn;



    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String password) {
        passwordConfirmed = password.equals(this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> validate() {
        List<String> violations = new ArrayList<>();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violationSet = validator.validate(this);

            for (ConstraintViolation<User> violation : violationSet) {
                violations.add(violation.getMessage());
            }
        } catch (Exception e) {
            System.out.println("VALIDATION ERROR!");
            e.printStackTrace();
        }

        return violations;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return this.username.equals(user.username) && this.password.equals(user.password);
    }
}
