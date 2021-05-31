package com.modularbank.services.exception;

import com.modularbank.services.dto.baseResponse.CommonResponse;
import com.modularbank.services.dto.baseResponse.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity notFoundException(CustomNotFoundException exception) {
        CommonResponse dataDTO = new CommonResponse();
        dataDTO.setStatus(exception.getCode());
        List<Messages> list=new ArrayList<>();
        list.add(new Messages( "error",exception.getMessage()));
        dataDTO.setMessages(list);
        return new ResponseEntity(dataDTO, HttpStatus.NOT_FOUND);
    }

}
