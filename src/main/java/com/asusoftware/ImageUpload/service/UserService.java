package com.asusoftware.ImageUpload.service;

import com.asusoftware.ImageUpload.model.CreateUserDto;
import com.asusoftware.ImageUpload.model.User;
import com.asusoftware.ImageUpload.model.UserDto;
import com.asusoftware.ImageUpload.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UploadFileService uploadFileService;

    public void create(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        user.setImageList(createUserDto.getImageList().stream().map(uploadFileService::uploadFile).collect(Collectors.toList()));
        userRepository.save(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList());
    }
}
