package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroFlatEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroFlatRepository extends JpaRepository<KokoroFlatEntity, UUID> {

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by bm_dein_slash_damage desc limit 50", nativeQuery = true)
  List<Object[]> findBmDeinSlashDamages();

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by bm_hyado_slash_damage desc limit 50", nativeQuery = true)
  List<Object[]> findBmHyadoSlashDamages();
}
