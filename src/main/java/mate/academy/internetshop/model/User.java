package mate.academy.internetshop.model;

import lombok.Getter;
import lombok.Setter;
import mate.academy.internetshop.service.idgenerators.UserIdGenerator;

import java.util.HashSet;
import java.util.Set;

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
    private String token;
//    private Set<Role> roles = new HashSet<>();
    private String roles;

    public User() {
        id = UserIdGenerator.getGeneratedId();
    }

    public User(String name, String surname, String email, String phone,
                String login, String password) {
//        this();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        token = name + surname;
    }

    public User(Long id, String name, String surname, String email,
                String phone, String login, String password, String token, String roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.token = token;
        this.roles = roles;
    }

//    public void addRole(Role role) { roles.add(role); }

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
