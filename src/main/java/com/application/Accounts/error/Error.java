package com.application.Accounts.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String message;
    private String method;
    private String requestURI;

    public Error(String message) {
        this.message = message;
    }

}
