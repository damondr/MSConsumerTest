package org.damon.st.consumer.service.impl;

import lombok.RequiredArgsConstructor;
import org.damon.st.consumer.model.Contact;
import org.damon.st.consumer.model.User;
import org.damon.st.consumer.repository.UsersRepository;
import org.damon.st.consumer.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public void createUser(User user) {
        for (Contact contact : user.getContacts()) {
            contact.setUser(user);
            contact.setUser_id(user.getId());
        }
        usersRepository.save(user);
    }
    @Transactional
    public void updateUser(User user) {
        for (Contact contact : user.getContacts()) {
            contact.setUser(user);
            contact.setUser_id(user.getId());
        }
        usersRepository.save(user);
    }
    @Transactional
    public void deleteUser(User user) {
        for (Contact contact : user.getContacts()) {
            contact.setUser(user);
            contact.setUser_id(user.getId());
        }
        usersRepository.delete(user);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }
    public List<User> searchByName(String name) {
        return usersRepository.findByNameContainingIgnoreCase(name);
    }
    public List<User> searchBySurname(String name) {
        return usersRepository.findBySurnameContainingIgnoreCase(name);
    }
    public List<User> searchByNameAndSurname(String name, String surname) {
        return usersRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
    }
}