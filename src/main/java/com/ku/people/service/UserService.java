package com.ku.people.service;


import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.entity.User;
import com.ku.people.filter.UserFilter;
import com.ku.people.mapper.UserMapper;
import com.ku.people.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserDto findById(Long id) {
        return userRepository.findById(id);
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

    public void update(UserSaveDto userSaveDto) {
        userRepository.update(userSaveDto);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}