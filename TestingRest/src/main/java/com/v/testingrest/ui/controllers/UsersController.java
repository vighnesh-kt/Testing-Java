package com.v.testingrest.ui.controllers;



import com.v.testingrest.service.UsersService;
import com.v.testingrest.shared.UserDto;
import com.v.testingrest.ui.request.UserDetailsRequestModel;
import com.v.testingrest.ui.response.UserRest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = new ModelMapper().map(userDetails, UserDto.class);

        UserDto createdUser = usersService.createUser(userDto);

        return modelMapper.map(createdUser, UserRest.class);
    }

    @GetMapping("/{userId}")
    public UserRest getUser(@PathVariable String userId) {
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(usersService.getUser(userId), returnValue);
        return returnValue;
    }

    @GetMapping
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<UserDto> users = usersService.getUsers(page, limit);

        Type listType = new TypeToken<List<UserRest>>() {
        }.getType();

        return new ModelMapper().map(users, listType);
    }
}
