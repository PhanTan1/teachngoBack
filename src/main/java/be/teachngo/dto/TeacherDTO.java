package be.teachngo.dto;


import be.teachngo.data.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherDTO {

    protected Long id;

    private String login;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phone;

    private Address adress;

    private boolean active;

    private Long teacherCoursID;

    private String biography;

    private float price;
}
