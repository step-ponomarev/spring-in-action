package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class RegistrationForm {
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Confirm password")
    private String confirmPassword;
    @NotBlank(message = "Full name is required")
    private String fullName;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Zip code is required")
    private String zip;
    @NotBlank(message = "Phone is required")
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                userName,
                passwordEncoder.encode(password),
                fullName,
                street,
                city,
                state,
                zip,
                phone
        );
    }
}
