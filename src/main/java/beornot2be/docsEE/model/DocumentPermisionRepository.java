package beornot2be.docsEE.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentPermisionRepository extends JpaRepository<DocumentPermision, Integer> {
    public Optional<DocumentPermision> findById(Integer id);
}