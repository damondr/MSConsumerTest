package org.damon.st.consumer.service;

import org.damon.st.consumer.model.User;
import org.damon.st.consumer.utils.UserOperation;

import java.util.List;

public interface UsersService {
    void processUserOperation(User user, UserOperation operation);
    List<User> findAll();
    List<User> searchByName(String name);
    List<User> searchBySurname(String surname);
    List<User> searchByNameAndSurname(String name, String surname);
}
