package nl.sogyo.ip.application.dto;

public record UserRegistrationDTO(
        String firstname,
        String lastName,
        String email,
        String username,
        String password
) {}
