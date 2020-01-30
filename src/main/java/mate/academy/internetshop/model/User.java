package mate.academy.internetshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String login;
    private String password;
    private byte[] salt;
    private String token;
    private Set<Role> roles = new HashSet<>();

    public User() { }

    public User(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, String surname, String email, String phone,
                String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        token = name + surname;
    }

    public User(Long id, String name, String surname, String email, String phone, String login, String password, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public User(String name, String surname, String email, String phone, String login, String password, String token) {
        this(name, surname, email, phone,login,password);
        this.token = token;
    }

    public void addRole(Role role) { roles.add(role); }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", roles=" + roles.toString() +
                '}';
    }
}
