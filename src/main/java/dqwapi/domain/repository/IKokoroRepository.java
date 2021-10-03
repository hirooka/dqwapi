package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroRepository extends JpaRepository<KokoroEntity, UUID> {
}
