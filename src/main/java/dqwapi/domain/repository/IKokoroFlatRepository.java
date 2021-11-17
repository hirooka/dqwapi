package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroFlatEntity;
import dqwapi.domain.model.kokoro.GradeType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroFlatRepository extends JpaRepository<KokoroFlatEntity, UUID> {

  List<KokoroFlatEntity> findFirstByIdAndGrade(final Integer id, final GradeType gradeType);

  // BATTLE_MASTER

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByBattleMasterDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SAGE

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySageOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySageDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // RANGER

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByRangerDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // ARMAMENTALIST

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByArmamentalistDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PALADIN

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPaladinDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SUPERSTAR

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findBySuperstarDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PIRATE

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_op_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_os_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_opos_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_opdx_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_ds_pattern from kokoro_combination where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findByPirateDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);


  // -------- JOB, ATTRIBUTE, RACE --------

  // BATTLE_MASTER

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_op_pattern from kokoro_combination_battle_master where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByBattleMasterOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_os_pattern from kokoro_combination_battle_master where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByBattleMasterOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_opos_pattern from kokoro_combination_battle_master where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByBattleMasterOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_opdx_pattern from kokoro_combination_battle_master where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByBattleMasterOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_ds_pattern from kokoro_combination_battle_master where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByBattleMasterDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SAGE

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_op_pattern from kokoro_combination_sage where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySageOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_os_pattern from kokoro_combination_sage where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySageOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_opos_pattern from kokoro_combination_sage where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySageOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_opdx_pattern from kokoro_combination_sage where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySageOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_sage_ds_pattern from kokoro_combination_sage where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySageDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // RANGER

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_op_pattern from kokoro_combination_ranger where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByRangerOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_os_pattern from kokoro_combination_ranger where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByRangerOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_opos_pattern from kokoro_combination_ranger where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByRangerOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_opdx_pattern from kokoro_combination_ranger where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByRangerOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_ranger_ds_pattern from kokoro_combination_ranger where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByRangerDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // ARMAMENTALIST

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_op_pattern from kokoro_combination_armamentalist where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByArmamentalistOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_os_pattern from kokoro_combination_armamentalis where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByArmamentalistOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_opos_pattern from kokoro_combination_armamentalis where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByArmamentalistOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_opdx_pattern from kokoro_combination_armamentalis where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByArmamentalistOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_armamentalist_ds_pattern from kokoro_combination_armamentalis where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByArmamentalistDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PALADIN

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_op_pattern from kokoro_combination_paladin where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPaladinOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_os_pattern from kokoro_combination_paladin where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPaladinOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_opos_pattern from kokoro_combination_paladin where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPaladinOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_opdx_pattern from kokoro_combination_paladin where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPaladinOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_paladin_ds_pattern from kokoro_combination_paladin where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPaladinDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // SUPERSTAR

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_op_pattern from kokoro_combination_superstar where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySuperstarOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_os_pattern from kokoro_combination_superstar where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySuperstarOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_opos_pattern from kokoro_combination_superstar where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySuperstarOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_opdx_pattern from kokoro_combination_superstar where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySuperstarOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_superstar_ds_pattern from kokoro_combination_superstar where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsBySuperstarDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  // PIRATE

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_op_pattern from kokoro_combination_pirate where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPirateOp(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_os_pattern from kokoro_combination_pirate where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPirateOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_opos_pattern from kokoro_combination_pirate where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPirateOpOs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_opdx_pattern from kokoro_combination_pirate where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPirateOpDx(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);

  @Query(value = "select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_pirate_ds_pattern from kokoro_combination_pirate where total_cost < ?1 and k0id not in ?2 and k1id not in ?2 and k2id not in ?2 and k3id not in ?2 and concat(k0id, '_', k0grade) not in ?3 and concat(k1id, '_', k1grade) not in ?3 and concat(k2id, '_', k2grade) not in ?3 and concat(k3id, '_', k3grade) not in ?3", nativeQuery = true)
  List<Object[]> findCombinationsByPirateDs(final Pageable pageable, final Integer cost, final List<Integer> nonBrides, final List<String> exclusions);
}
