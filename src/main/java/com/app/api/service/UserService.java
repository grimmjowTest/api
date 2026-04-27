package com.app.api.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.api.dto.user.UserRequestDTO;
import com.app.api.entity.Movie;
import com.app.api.entity.User;
import com.app.api.mapper.UserMapper;
import com.app.api.repository.MovieRepository;
import com.app.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Set<Movie> getUserMovies(Long userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user.getMovies();
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmail(userRequestDTO.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        User user = UserMapper.toEntity(userRequestDTO);

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (userRepository.existsByUsernameAndIdNot(userRequestDTO.getUsername(), id))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmailAndIdNot(userRequestDTO.getEmail(), id))
            throw new IllegalArgumentException("Email already exists");

        UserMapper.updateEntity(existingUser, userRequestDTO);

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Transactional
    public void addMovieToUser(Long userId, Long movieId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Movie movie = movieRepository.findById(movieId)
                                     .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        if (user.getMovies().contains(movie))
            throw new IllegalArgumentException("Movie already in favorites");

        user.getMovies().add(movie);

        userRepository.save(user);
    }
}