package com.bureauworks.translators.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Iverson Pereira
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TranslatorNotFoundException extends RuntimeException {

    public TranslatorNotFoundException(String msg) {
        super(msg);
    }

    public TranslatorNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
