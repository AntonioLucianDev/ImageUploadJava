package com.asusoftware.ImageUpload.controller;

import com.asusoftware.ImageUpload.model.CreateUserDto;
import com.asusoftware.ImageUpload.model.UserDto;
import com.asusoftware.ImageUpload.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void create(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.create(createUserDto);
    }

    @GetMapping("/all")
    public List<UserDto> findAll() {
       return userService.findAll();
    }
}
