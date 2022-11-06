package com.example.test.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.istack.Nullable;
import org.slf4j.Logger;


public class ControllerApplicationException extends WebApplicationException {

    public ControllerApplicationException(Logger logger, String message, Response.Status status) {
        this(logger, message, status.getStatusCode(), null);
    }

    public ControllerApplicationException(Logger logger, String message, int status) {
        this(logger, message, status, null);
    }

    public ControllerApplicationException(Logger logger, String message, Response.Status status, @Nullable Throwable e) {
        this(logger, message, status.getStatusCode(), e);
    }

    public ControllerApplicationException(Logger logger, String message, int status, @Nullable Throwable e) {
        super(message, status);
        if (status >= 300 && status < 500) {
            if (e == null) {
                logger.info(message);
            } else {
                logger.info(message + " exception: " + e.getMessage());
            }
        } else {
            if (e == null) {
                logger.error(message);
            } else {
                logger.error(message, e);
            }
        }
    }
}