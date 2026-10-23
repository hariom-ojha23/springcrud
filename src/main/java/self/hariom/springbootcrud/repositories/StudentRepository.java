package self.hariom.springbootcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.hariom.springbootcrud.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    public List<StudentEntity> findByDeletedIsFalse();

    public Optional<StudentEntity> findByIdAndDeletedIsFalse(Long id);

    public boolean existsByEmail(String email);
}
