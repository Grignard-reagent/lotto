package com.lab.lotto.controller;

import com.lab.lotto.dto.request.LoginRequest;
import com.lab.lotto.dto.response.LoginResponse;
import com.lab.lotto.dto.response.ResponseResult;
import com.lab.lotto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseResult.success(authService.login(request));
    }
} 