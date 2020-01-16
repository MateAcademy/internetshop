package mate.academy.internetshop.model;

import mate.academy.internetshop.service.idgenerators.UserIdGenerator;

public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String login;
    private String password;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User() {
        id = UserIdGenerator.getGeneratedId();
    }

    public User(String name, String surname, String email, String phone,
                String login, String password) {
        this();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\'' + '}';
    }
}
