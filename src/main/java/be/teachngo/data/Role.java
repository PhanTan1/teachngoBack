package be.teachngo.data;

public enum Role {

    ADMIN, STUDENT, TEACHER;


    public String getRole() {
        return "ROLE_" + name();
    }

}
