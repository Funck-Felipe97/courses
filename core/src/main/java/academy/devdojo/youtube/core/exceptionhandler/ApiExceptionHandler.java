package academy.devdojo.youtube.core.exceptionhandler;

import academy.devdojo.youtube.core.service.MessageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final String userMessage = messageService.getMessage("message.invalid");
        final String devMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        return super.handleExceptionInternal(ex, asList(new Error(userMessage, devMessage)), headers, status, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageService.getMessage("resource.not-found");
        String devMessage = ex.toString();
        return super.handleExceptionInternal(ex, asList(new Error(userMessage, devMessage)), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMessage = messageService.getMessage("operation.not-allowed");
        String devMessage = ExceptionUtils.getRootCauseMessage(ex);
        return super.handleExceptionInternal(ex, asList(new Error(userMessage, devMessage)), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        String userMessage = ex.getMessage();
        String devMessage = ex.toString();
        return super.handleExceptionInternal(ex, asList(new Error(userMessage, devMessage)), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, getErrorList(ex.getBindingResult()), headers, status, request);
    }

    private List<Error> getErrorList(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new Error(fieldError.getDefaultMessage(), fieldError.toString()))
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    @Getter
    public static class Error {
        private final String userMessage;
        private final String devMessage;
    }

}
