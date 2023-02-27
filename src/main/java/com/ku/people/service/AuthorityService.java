package com.ku.people.service;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;
import com.ku.people.mapper.AuthorityMapper;
import com.ku.people.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    private AuthorityMapper authorityMapper;

    public AuthorityDto findById(Long id) {
        Authority authority = authorityRepository.findById(id).get();
        return authorityMapper.toDto(authority);
    }

    public List<AuthorityListDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorityMapper.toListDto(authorities);
    }

    public Authority save(AuthoritySaveDto authoritySaveDto) {
        Authority authority = authorityMapper.fromSaveDto(authoritySaveDto);
        return authorityRepository.save(authority);
    }

}
