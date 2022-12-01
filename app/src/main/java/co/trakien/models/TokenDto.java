package co.trakien.models;

import java.util.Date;

public class TokenDto {

    private String token;

    private Date expirationDate;

    /**
     * This method creates a TokenDto
     *
     * @param token
     * @param expirationDate
     */
    public TokenDto(String token, Date expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}