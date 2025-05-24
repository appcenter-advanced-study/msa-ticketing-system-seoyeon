package com.example.ticketstockservice.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundTicketStockException {
    public NotFoundTicketStockException(final String message) {
        super();
    }

    public NotFoundTicketStockException() {
        this("티켓스톡 객체를 찾을 수 없습니다.");
    }
}
