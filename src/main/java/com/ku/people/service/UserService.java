package com.ku.people.service;


import com.ku.people.entity.User;
import com.ku.people.repository.dataJPA.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Query(value = "FROM User u\n" +
//            "   LEFT JOIN FETCH u.roles\n" +
//            "   LEFT JOIN FETCH u.details\n" +
//            "WHERE u.id = :id", nativeQuery = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
