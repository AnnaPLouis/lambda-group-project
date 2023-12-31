package com.cydeo.exception;

import com.cydeo.annotation.AccountingExceptionMessage;
import com.cydeo.dto.common.DefaultExceptionMessageDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Throwable.class})
    public String genericException(Throwable exception, HandlerMethod handlerMethod, Model model) {
        exception.printStackTrace();
        String message = "Something went wrong!";
        Optional<DefaultExceptionMessageDto> defaultMessage = getMessageFromAnnotation(handlerMethod.getMethod());
        if (defaultMessage.isPresent()) {
            message = defaultMessage.get().getMessage();
        } else if (exception.getMessage() != null) {
            message = exception.getMessage();
        }
        model.addAttribute("message", message);
        return "error";
    }

    private Optional<DefaultExceptionMessageDto> getMessageFromAnnotation(Method method) {
        AccountingExceptionMessage defaultExceptionMessage = method
                .getAnnotation(AccountingExceptionMessage.class);
        if (defaultExceptionMessage != null && !defaultExceptionMessage.defaultMessage().equals("Something went wrong!")) {
            DefaultExceptionMessageDto defaultExceptionMessageDto = DefaultExceptionMessageDto
                    .builder()
                    .message(defaultExceptionMessage.defaultMessage())
                    .build();
            return Optional.of(defaultExceptionMessageDto);
        }
        return Optional.empty();
    }
}