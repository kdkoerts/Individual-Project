package nl.sogyo.domain;

public record User(
        Long id,
        String firstname,
        String lastName,
        String email,
        String username,
        String password
) {}
