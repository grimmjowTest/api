package com.app.api.mapper;

import com.app.api.dto.user.UserRequestDTO;
import com.app.api.dto.user.UserResponseDTO;
import com.app.api.entity.User;

public class UserMapper {
    
    public static User toEntity (UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null)
            return null;

        User user = new User();

        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        return user;
    }

    public static UserResponseDTO toDTO (User user) {
        if (user == null) 
            return null;

        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }

    public static void updateEntity(User user, UserRequestDTO userRequestDTO) {
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
    }
}
