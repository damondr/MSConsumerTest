package org.damon.st.consumer.service.impl;

import lombok.RequiredArgsConstructor;
import org.damon.st.consumer.exception.UsersException;
import org.damon.st.consumer.model.ContactKey;
import org.damon.st.consumer.model.User;
import org.damon.st.consumer.repository.UsersRepository;
import org.damon.st.consumer.service.UsersService;
import org.damon.st.consumer.utils.UserOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public void processUserOperation(User user, UserOperation userOperation) {
        updateContacts(user);
        switch (userOperation) {
            case CREATE:
            case UPDATE:
                saveOrUpdateUser(user);
                break;
            case DELETE:
                deleteUser(user);
                break;
            default:
                throw new UsersException("Operation is not supported");
        }
    }
    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }
    @Override
    public List<User> searchByName(String name) {
        return usersRepository.findByNameContainingIgnoreCase(name);
    }
    @Override
    public List<User> searchBySurname(String name) {
        return usersRepository.findBySurnameContainingIgnoreCase(name);
    }
    @Override
    public List<User> searchByNameAndSurname(String name, String surname) {
        return usersRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
    }

    private void saveOrUpdateUser(User user) {
        usersRepository.save(user);
    }

    private void deleteUser(User user) {
        usersRepository.delete(user);
    }

    private void updateContacts(User user) {
        user.getContacts().forEach(contact -> {
            if (contact.getId() == null) {
                contact.setId(new ContactKey(user.getId(), contact.getType()));
            }
        });
    }
}