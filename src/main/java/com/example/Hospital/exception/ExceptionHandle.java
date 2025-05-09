package com.example.Hospital.exception;


import com.example.Hospital.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException runtimeException){
        ApiResponse apiResponse= new ApiResponse<>();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMess(ErrorCode.UNCATEGORIZED_EXCEPTION.getMess());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> HandleAppException(AppException appException){
        ErrorCode errorCode=appException.getErrorCode();
        ApiResponse apiResponse=new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMess(errorCode.getMess());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
