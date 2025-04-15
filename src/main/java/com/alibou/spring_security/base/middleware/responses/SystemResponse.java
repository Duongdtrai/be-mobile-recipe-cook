package com.alibou.spring_security.base.middleware.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemResponse<T> {
    private int status;

    private String message;

    private T data;

    public SystemResponse() {
    }


    public SystemResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public SystemResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
