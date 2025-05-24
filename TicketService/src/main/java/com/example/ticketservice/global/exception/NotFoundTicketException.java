package com.example.ticketservice.global.exception;

public class NotFoundTicketException extends RuntimeException{
    public NotFoundTicketException(final String message) {
        super(message);
    }

    public NotFoundTicketException() {
        this("티켓을 찾을 수 없습니다.");
    }
}
