package ru.perfumess.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {

    private ResponseStatus responseStatus;
    private Object payload;

    public Response(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Response(int statusCode, String message) {
        this.responseStatus = new ResponseStatus(statusCode, message);
    }

    public Response(int statusCode) {
        this.responseStatus = new ResponseStatus(statusCode);
    }

    public Response(Object payload, ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
        this.payload = payload;
    }

    public Response(Object payload, int statusCode) {
        this.payload = payload;
        this.responseStatus = new ResponseStatus(statusCode);
    }

    public Response(Object payload, HttpStatus httpStatus) {
        this.payload = payload;
        this.responseStatus = new ResponseStatus(httpStatus);
    }

    public Response(HttpStatus httpStatus) {
        this.responseStatus = new ResponseStatus(httpStatus);
    }

    public Response(Object payload, int statusCode, String message) {
        this.payload = payload;
        this.responseStatus = new ResponseStatus(statusCode, message);
    }
}
