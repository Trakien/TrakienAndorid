package co.trakien.models;

public class LoginDto {
    String email;
    String password;

    public LoginDto() {
    }

    /**
     * This method creates a LoginDto
     *
     * @param email
     * @param password
     */
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Get the email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }
}
