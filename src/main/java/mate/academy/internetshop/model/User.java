package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private byte[] salt;

    @Column(name = "token")
    private String token;

    private Set<Role> roles = new HashSet<>();

    public User() { }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
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

    public User(Long id, String name, String surname, String email, String phone,
                String login, String password, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public User(String name, String surname, String email, String phone, String login,
                String password, String token) {
        this(name, surname, email, phone,login,password);
        this.token = token;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", token='" + token + '\''
                + ", roles=" + roles.toString() + '}';
    }
}
