package com.upskill.services;

import com.upskill.mappers.MapStructMapper;
import com.upskill.models.dto.UserGetDto;
import com.upskill.models.dto.UserPostDto;
import com.upskill.repositories.UserRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService
//        implements UserDetailsService
{
    private final MapStructMapper mapStructMapper;
    private final UserRepository userRepository;

    @Transactional
    public Optional<List<UserGetDto>> getAll() {
        return Optional.of(userRepository.getAll());
    }

    @Transactional
    public Optional<UserGetDto> getById(Long id) {
        return Optional.of(userRepository.getById(id));
    }

    @Transactional
    public UserPostDto getByUsername (String username){
        return userRepository.getByUsername(username);
    }

    @Transactional
    public void create(UserPostDto userPostDto) {
        userRepository.save(mapStructMapper.userPostDtoToUser(userPostDto));
    }

    @Transactional
    public void update(UserPostDto userPostDto) {
        userRepository.save(mapStructMapper.userPostDtoToUser(userPostDto));
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserPostDto user = getByUsername(username);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", username));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), true, true, true,
//                true, new HashSet<>());
//    }
}
