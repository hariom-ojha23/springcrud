package self.hariom.springbootcrud.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @Min(value = 101, message = "Roll number must be at least 101")
    @Max(value = 110, message = "Roll number must be at most 110")
    private Integer rollNo;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;
}
