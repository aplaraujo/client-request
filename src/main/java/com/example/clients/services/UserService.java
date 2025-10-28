package com.example.clients.services;

import com.example.clients.dto.UserDTO;
import com.example.clients.entities.User;
import com.example.clients.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> result = userRepository.findAll(pageable);
        return result.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();
        return result.stream().map(x -> new UserDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id).get();
        return new UserDTO(entity);
    }
}
