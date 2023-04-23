package racingcar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RacingCarControllerAdvice {

    private static final String UNEXPECTED_ERROR_LOG_FORMAT = "예상치 못한 에러 발생 : " + System.lineSeparator() + "{}";
    //좀 더 적절한 메시지가 있을지 고민해보기.
    private static final String UNEXPECTED_ERROR_MESSAGE = "네트워크 지연으로 다시 시도해주시기 바랍니다.";
    private static final String CANNOT_DESERIALIZE_REQUEST_ERROR_MESSAGE = "타입에 맞는 값을 입력해주시길 바랍니다.";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> loggingUnExpectedException(final RuntimeException e) {
        logger.error(UNEXPECTED_ERROR_LOG_FORMAT, convertToString(e));
        return ResponseEntity.internalServerError()
                .body(UNEXPECTED_ERROR_MESSAGE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle(final HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(CANNOT_DESERIALIZE_REQUEST_ERROR_MESSAGE);
    }

    private String convertToString(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
