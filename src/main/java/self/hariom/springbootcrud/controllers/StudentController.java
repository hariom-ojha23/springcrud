package self.hariom.springbootcrud.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.hariom.springbootcrud.dtos.StudentRequestDTO;
import self.hariom.springbootcrud.dtos.StudentResponseDTO;
import self.hariom.springbootcrud.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Value("${app.welcome.greetMessage}")
    private String greetMessage;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     *
     * Create Student
     */
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO student
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    /**
     *
     * Greet Student
     */
    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok(greetMessage);
    }

    /**
     *
     * Get All Student
     */
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }

    /**
     *
     * Get Student Details
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        StudentResponseDTO student = studentService.getStudentDetails(id);
        return ResponseEntity.ok(student);
    }

    /**
     *
     * Update Student
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO student
    ) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     *
     * Delete Student: Hard
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Student Deleted");
    }

    /**
     *
     * Delete Student: Soft
     */
    @PatchMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentSoft(@PathVariable Long id) {
        studentService.deleteStudentSoft(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Student Deleted");
    }
}
