package br.com.terracotabackend.infra.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    public RequiredObjectIsNullException() {
        super("Required Object is null");
    }

    public RequiredObjectIsNullException(String msg) {
        super(msg);
    }
}
