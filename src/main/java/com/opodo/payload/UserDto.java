package com.opodo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
        private Long id;
        private String username;
        private String email;
        private String password;

    }
