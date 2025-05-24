package com.pioneerpixel.demo.adapter.web;

import com.pioneerpixel.demo.adapter.web.dto.GetUserRequest;
import com.pioneerpixel.demo.adapter.web.dto.GetUserResponse;
import com.pioneerpixel.demo.adapter.web.dto.LoginRequest;
import com.pioneerpixel.demo.adapter.web.dto.LoginResponse;
import com.pioneerpixel.demo.adapter.web.mapper.UserMapper;
import com.pioneerpixel.demo.business.dto.UserFilteringAndPagingParams;
import com.pioneerpixel.demo.business.usecase.GetUsersUseCase;
import com.pioneerpixel.demo.business.usecase.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(name = "User Controller", description = "Users related methods")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserMapper userMapper;
    private final LoginUseCase login;
    private final GetUsersUseCase getUser;

    @Operation(summary = "Login to the system")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        String jwt = login.execute(request.getPassword(), request.getPhone());
        return LoginResponse.of(jwt);
    }

    @Operation(summary = "Get users with filtering and pagination")
    @GetMapping
    public List<GetUserResponse> getAll(GetUserRequest request, Pageable pageable) {
        UserFilteringAndPagingParams filter = userMapper.mapToDomain(request, pageable);
        return getUser.execute(filter).stream()
            .map(userMapper::mapToDto)
            .toList();
    }
}
