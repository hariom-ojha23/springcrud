package self.hariom.springbootcrud.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import self.hariom.springbootcrud.exceptions.DuplicateResourceException;
import self.hariom.springbootcrud.exceptions.ResourceNotFoundException;
import self.hariom.springbootcrud.dtos.StudentRequestDTO;
import self.hariom.springbootcrud.dtos.StudentResponseDTO;
import self.hariom.springbootcrud.entities.StudentEntity;
import self.hariom.springbootcrud.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Create Student
     */
    public StudentResponseDTO createStudent(StudentRequestDTO student) {
        StudentEntity studentEntity = mapToEntity(student);
        studentEntity.setDeleted(false);

        if (emailExist(student.getEmail())) {
            throw new DuplicateResourceException("Student with email " + student.getEmail() + " already exist");
        }

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return mapToDto(savedStudent);
    }

    /**
     * Get All Student
     */
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findByDeletedIsFalse();
        return studentEntities
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Get Student By Id
     */
    public StudentResponseDTO getStudentDetails(Long id) {
        StudentEntity student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));

        return mapToDto(student);
    }

    /**
     * Update Student
     */
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO student) {
        StudentEntity existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));

        modelMapper.map(student, existingStudent);
        StudentEntity updatedStudent = studentRepository.save(existingStudent);

        return mapToDto(updatedStudent);
    }

    /**
     * Delete Student: Hard
     */
    public void deleteStudent(Long id) {
        StudentEntity student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));

        studentRepository.delete(student);
    }

    /**
     * Delete Student: Soft
     */
    public void deleteStudentSoft(Long id) {
        StudentEntity student = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));

        student.setDeleted(true);
        studentRepository.save(student);
    }

    private boolean emailExist(String email) {
        return studentRepository.existsByEmail(email);
    }

    private StudentResponseDTO mapToDto(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, StudentResponseDTO.class);
    }

    private <T> StudentEntity mapToEntity(T studentRequestDTO) {
        return modelMapper.map(studentRequestDTO, StudentEntity.class);
    }
}
