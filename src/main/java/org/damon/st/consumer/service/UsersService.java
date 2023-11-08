package org.damon.st.consumer.service;

import org.damon.st.consumer.model.User;

import java.util.List;

public interface UsersService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

    List<User> findAll();
    List<User> searchByName(String name);
    List<User> searchBySurname(String surname);
    List<User> searchByNameAndSurname(String name, String surname);
}
