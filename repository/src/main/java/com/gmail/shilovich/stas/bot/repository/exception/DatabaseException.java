package com.gmail.shilovich.stas.bot.repository.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }
}
