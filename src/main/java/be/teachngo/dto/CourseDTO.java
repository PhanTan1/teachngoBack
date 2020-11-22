package be.teachngo.dto;

import be.teachngo.data.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CourseDTO {

    private Long id;
    private String subject;
    private String description;
    private int size;
    private Category category;
    private List<TeacherDTO> teachers = new ArrayList<>();
}
