package com.opodo.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String email;
    private String password;
}
