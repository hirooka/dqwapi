package dqwapi.domain.repository;

import dqwapi.domain.entity.CombinationEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICombinationRepository extends JpaRepository<CombinationEntity, UUID> {
}
