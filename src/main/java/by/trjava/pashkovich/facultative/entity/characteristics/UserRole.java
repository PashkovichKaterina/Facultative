package by.trjava.pashkovich.facultative.entity.characteristics;

public enum UserRole {
    ADMINISTRATOR(1), TEACHER(2), STUDENT(3);

    private int roleId;

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static UserRole getRole(int roleId) {
        UserRole role = null;
        switch (roleId) {
            case 1:
                role = ADMINISTRATOR;
                break;
            case 2:
                role = TEACHER;
                break;
            case 3:
                role = STUDENT;
                break;
        }
        return role;
    }
}
