package co.trakien.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String token;
    private String email;
    private static LoggedInUser _instance = new LoggedInUser();

    private LoggedInUser(){};

    public static LoggedInUser getInstance(){
        return _instance;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
    public void setToken(String token){this.token = "Bearer " + token;}
    public void setEmail(String email){this.email = email;}
}
