package com.example.artisophy.dao.elements;

import java.util.Objects;

public class User implements UserInterface {
    private String name;
    private String surnames;
    private String username;
    private String email;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String surnames, String username, String email, String password) {
        this.name = name;
        this.surnames = surnames;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurnames() {
        return surnames;
    }

    @Override
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user=(User) o;
        return username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surnames, username, email, password);
    }
}
