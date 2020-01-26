package mate.academy.internetshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private String token;
    private Set<Role> roles = new HashSet<>();

    public User() { }

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

    public User(String name, String surname, String email, String phone, String login, String password, String token) {
        this(name, surname, email, phone,login,password);
        this.token = token;
    }

    public void addRole(Role role) { roles.add(role); }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\'' + '}';
    }

//    public static void main(String[] args) {
//        User user = new User();
//        user.addRole(Role.of("ADMIN"));
//        System.out.println(user);
//    }
}
