package mate.academy.internetshop.model;

import mate.academy.internetshop.service.idgenerators.RoleIdGenerator;

/**
 * @author Sergey Klunniy
 */
public class Role {
    private final Long id;
    private RoleName roleName;

    public Role() {
        this.id = RoleIdGenerator.getGeneratedId();
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = RoleName.valueOf(roleName);
    }

    public Long getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN;
    }

    public static Role of(String roleName) {
       return new Role(RoleName.valueOf(roleName));
    }

    @Override
    public String toString() {
        return "role=" + roleName;
    }
}
