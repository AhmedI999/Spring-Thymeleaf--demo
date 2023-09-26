package com.springboot.thymeleafsecuritydemo.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WebUser {
    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String userName;
    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String password;

    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String firstName;

    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    private String lastName;

    @NotNull(message = "Field is required")
    @Size(min = 1, message = "Field is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
}
