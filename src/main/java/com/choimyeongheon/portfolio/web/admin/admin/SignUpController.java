package com.choimyeongheon.portfolio.web.admin.admin;

import com.choimyeongheon.portfolio.domain.admin.service.AdminService;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class SignUpController {

    private final AdminService adminService;

    @PostMapping("/sign-up")
    public ResponseEntity<Long> signUp(@RequestBody SignUpRequest request) {
        Long savedAdminId = adminService.signUp(request);
        return new ResponseEntity<>(savedAdminId, HttpStatus.CREATED);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
