package com.opodo.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class PassengerDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "passenger firstName should have at least 2 character")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "passenger lastName should have at least 2 character")
    private String surName;
    @NotEmpty
    private String gender;
    @NotNull
    @Past(message = "Date of birth should be in the past")
    private Date dateOfBirth;
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    private String phone;
    private String email;
    private String confirmEmail;
    private String countryCode;
    private String address;
    private String postcode;
    private String city;
    private String country;

}

