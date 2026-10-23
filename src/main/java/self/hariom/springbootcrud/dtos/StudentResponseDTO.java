package self.hariom.springbootcrud.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Integer rollNo;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
