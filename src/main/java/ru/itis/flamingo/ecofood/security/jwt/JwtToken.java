package ru.itis.flamingo.ecofood.security.jwt;

public class JwtToken {

    public JwtToken(String token) { this.token = token; }

    private String token;

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

}
