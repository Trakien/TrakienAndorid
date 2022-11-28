package co.trakien.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String token;
    private String email;

    public LoggedInUser(String token, String email) {
        this.token = token;
        this.email = email;
    }
    public LoggedInUser() {
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
    public void setToken(String token){this.token = token;}
    public void setEmail(String email){this.email = email;}
}
