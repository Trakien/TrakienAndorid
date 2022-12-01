package co.trakien.models;

public class CustomerDto {

    private String id;
    private String name;
    private String passwd;
    private String email;
    private String lastName;
    private String createdAt;

    public CustomerDto() {
    }

    /**
     * This method creates a CustomerDto
     *
     * @param customer
     */
    public CustomerDto(String id, String name, String email, String lastName, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    /**
     * This method creates a CustomerDto
     *
     * @param customer
     */
    public CustomerDto(String id, String name, String passwd, String email, String lastName, String createdAt) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public CustomerDto(String name, String email, String lastName, String createdAt) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    /**
     * Get the id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * Set the email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the lastName
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastName
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the createdAt
     *
     * @return String
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the createdAt
     *
     * @param createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method get the password
     *
     * @param String
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method set the password
     *
     * @param passwd
     */
    public void setPasswd(String paswd) {
        this.passwd = paswd;
    }

}