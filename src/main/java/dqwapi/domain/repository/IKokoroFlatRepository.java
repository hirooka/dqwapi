package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroFlatEntity;
import dqwapi.domain.model.kokoro.RankType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroFlatRepository extends JpaRepository<KokoroFlatEntity, UUID> {

  List<KokoroFlatEntity> findFirstByIdAndRank(final Integer id, final RankType rankType);

  // BATTLE_MASTER

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SAGE

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySageDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // RANGER

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // ARMAMENTALIST

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_armamentalist_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_armamentalist_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_armamentalist_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_armamentalist_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_armamentalist_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PALADIN

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SUPERSTAR

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_superstar_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_superstar_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_superstar_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_superstar_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_superstar_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PIRATE

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_pirate_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_pirate_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_pirate_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_pirate_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_pirate_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0rank) not in ?3 and concat(k1id, '_', k1rank) not in ?3 and concat(k2id, '_', k2rank) not in ?3 and concat(k3id, '_', k3rank) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);
}
