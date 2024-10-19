package com.choimyeongheon.portfolio.web.admin.admin;

import com.choimyeongheon.portfolio.domain.admin.service.SignUpRequestService;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequestApiDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {

    private final SignUpRequestService signUpRequestService;

    @PostMapping
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpRequestApiDto request) {
        Long savedAdminId = signUpRequestService.signUp(request);
        return new ResponseEntity<>(savedAdminId, HttpStatus.CREATED);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
