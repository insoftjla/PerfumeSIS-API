package ru.perfumess.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseStatus {

    private int resultCode;
    private String message;

    public ResponseStatus(int resultCode) {
        this.resultCode = resultCode;
        HttpStatus httpStatus = HttpStatus.resolve(resultCode);
        message = httpStatus != null ? httpStatus.getReasonPhrase() : "NO MESSAGE";
    }

    public ResponseStatus(HttpStatus httpStatus) {
        this.resultCode = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

    public ResponseStatus(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
}
