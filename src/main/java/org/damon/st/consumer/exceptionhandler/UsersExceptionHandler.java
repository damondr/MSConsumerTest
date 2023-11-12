package org.damon.st.consumer.exceptionhandler;

import org.damon.st.consumer.dto.ErrorDto;
import org.damon.st.consumer.listener.UserListener;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(assignableTypes = UserListener.class)
public class UsersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ErrorDto handleException(Exception e) {
        return new ErrorDto(99, e.getMessage());
    }
}