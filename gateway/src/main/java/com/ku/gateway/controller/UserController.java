package com.ku.gateway.controller;

import com.ku.gateway.filter.UserFilter;
import com.ku.gateway.service.UserService;
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
import java.util.Optional;

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
    public String findById(
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
    public String search(
            @RequestParam(value = "name", required = false) Optional<String> name,
            @RequestParam(value = "surname", required = false) Optional<String> surname,
            @RequestParam(value = "username", required = false) Optional<String> username,
            @RequestParam(value = "offset", required = false, defaultValue = "10") Optional<Integer> offset,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Optional<Integer> limit
    ) {
        return userService.search(new UserFilter()
                .setName(name)
                .setSurname(surname)
                .setUsername(username)
                .setLimit(limit)
                .setOffset(offset));
    }

//    @PostMapping
//    @Operation(summary = "Save user")
//    public UserSaveDto save(
//            @RequestBody
//            @Parameter(description = "Information about saving user", required = true) UserSaveDto userSaveDto) {
//        return userService.save(userSaveDto);
//    }
//
//    @PutMapping
//    @Operation(summary = "Update user")
//    public void update(
//            @RequestBody
//            @Parameter(description = "Information about updating user", required = true) UserSaveDto userSaveDto) {
//        userService.update(userSaveDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Delete user")
//    public void delete(@PathVariable("id")
//                       @Parameter(description = "Id of deleting user", example = "99", required = true) Long id
//    ) {
//        userService.delete(id);
//    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
