package com.subway.railme.login;

public class JoinUserAccount {
    private String Email;
    private String Password;
    private String IdToken; // 파이어베이스 uid (고유 토큰 정보)

    public void setIdToken(String idToken) {
        this.IdToken = idToken;
    }

    public String getIdToken() {
        return IdToken;
    }

    public JoinUserAccount() {}

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
