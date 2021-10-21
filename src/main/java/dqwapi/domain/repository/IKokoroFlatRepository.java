package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroFlatEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroFlatRepository extends JpaRepository<KokoroFlatEntity, UUID> {

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_patter from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_ds_pattern from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern from kokoro_flat_cb_mv where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

}
