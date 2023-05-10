package com.example.artisophy.dao.elements;

public interface UserInterface {

    String getName();

    void setName(String name);

    String getSurnames();

    void setSurnames(String surnames);

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    @Override
    boolean equals(Object o);

    @Override
    String toString();

    @Override
    int hashCode();
}
