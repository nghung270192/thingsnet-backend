package org.thingsnet.application.DTO;

public class AuthDTO {

    public static record LoginRequest(String email, String password) {
        // Constructor, getters, and setters are automatically generated
    }

    public static record Response(String message, String token) {
        // Constructor, getters, and setters are automatically generated
    }

    public static record Register(String email, String password, String role) {
        // Constructor, getters, and setters are automatically generated
    }
}
