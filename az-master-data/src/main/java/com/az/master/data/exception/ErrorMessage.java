package com.az.master.data.exception;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Getter
@Builder(builderMethodName = "of")
public class ErrorMessage {

    private LocalDateTime localDateTime;
    private String message;
    private HttpStatus httpStatus;
    private String path;
}