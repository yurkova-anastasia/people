package com.ku.people.controller;

import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.filter.UserFilter;
import com.ku.people.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User controller", description = "The User API")
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Find User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", content =@Content(schema = @Schema(example = "User not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserDto findById(
            @PathVariable("id")
            @Parameter(description = "User id", example = "99", required = true) Long id
    ) {
        return userService.findById(id);
    }

//    @GetMapping
//    @Operation(summary = "Find all users")
//    public List<UserListDto> findAll() {
//        return userService.findAll();
//    }

    @GetMapping
    @Operation(summary = "Find users by filter")
    public List<UserDto> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "offset", required = false, defaultValue = "10") Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
    ) {
       UserFilter userFilter = new UserFilter()
                .setName(name)
                .setSurname(surname)
                .setUsername(username)
                .setOffset(offset)
                .setLimit(limit);
        return userService.search(userFilter);
    }

    @PostMapping
    @Operation(summary = "Save user")
    public UserSaveDto save(
            @RequestBody
            @Parameter(description = "Information about saving user", required = true) UserSaveDto userSaveDto) {
        return userService.save(userSaveDto);
    }

    @PutMapping
    @Operation(summary = "Update user")
    public Boolean update(
            @RequestBody
            @Parameter(description = "Information about updating user", required = true) UserSaveDto userSaveDto) {
        userService.update(userSaveDto);
        return true;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public Boolean delete(@PathVariable("id")
                       @Parameter(description = "Id of deleting user", example = "99", required = true) Long id
    ) {
        userService.delete(id);
        return true;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
