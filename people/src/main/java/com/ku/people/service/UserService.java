package com.ku.people.service;


import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.exception.ServiceException;
import com.ku.people.filter.UserFilter;
import com.ku.people.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserDto findById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ServiceException(String.format("User with id = %d was not found", id), exception, HttpStatus.NOT_FOUND);
        }
    }

    public List<UserListDto> findAll() {
        return userRepository.findAll();

    }

    public List<UserDto> search(UserFilter userFilter) {
        return userRepository.search(userFilter);
    }

    public UserSaveDto save(UserSaveDto userSaveDto) {
        return userRepository.save(userSaveDto);
    }

    public Boolean update(UserSaveDto userSaveDto) {
        userRepository.update(userSaveDto);
        return true;
    }

    public Boolean delete(Long id) {
        userRepository.delete(id);
        return true;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}