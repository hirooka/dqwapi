package dqwapi.domain.repository;

import dqwapi.domain.entity.KokoroFlatEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKokoroFlatRepository extends JpaRepository<KokoroFlatEntity, UUID> {

  // Battle Master, Slash

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_bagi_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterBagiSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_dein_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterDeinSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_doruma_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterDorumaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_gira_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterGiraSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_hyado_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterHyadoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_io_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterIoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_jibaria_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterJibariaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_mera_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterMeraSlashDamages(final Integer limit);

  // Battle Master, Hit

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_bagi_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterBagiHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_dein_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterDeinHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_doruma_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterDorumaHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_gira_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterGiraHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_hyado_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterHyadoHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_io_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterIoHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_jibaria_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterJibariaHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_battle_master_op_pattern "
      + "from kokoro_flat_cb_mv order by battle_master_mera_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findBattleMasterMeraHitDamages(final Integer limit);

  // Ranger, Slash

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_bagi_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerBagiSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_dein_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDeinSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_doruma_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDorumaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_gira_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerGiraSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_hyado_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerHyadoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_io_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerIoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_jibaria_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerJibariaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_mera_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerMeraSlashDamages(final Integer limit);

  // Battle Master, Hit

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_bagi_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerBagiHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_dein_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDeinHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_doruma_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDorumaHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_gira_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerGiraHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_hyado_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerHyadoHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_io_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerIoHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_jibaria_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerJibariaHitDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_op_pattern "
      + "from kokoro_flat_cb_mv order by ranger_mera_hit_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerMeraHitDamages(final Integer limit);

  // Battle Master, Breath

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_bagi_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerBagiBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_dein_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDeinBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_doruma_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerDorumaBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_gira_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerGiraBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_hyado_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerHyadoBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_io_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerIoBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_jibaria_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerJibariaBreathDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_ranger_opdx_pattern "
      + "from kokoro_flat_cb_mv order by ranger_mera_breath_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findRangerMeraBreathDamages(final Integer limit);

  // Sage, Spell

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_bagi_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageBagiSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_dein_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageDeinSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_doruma_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageDorumaSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_gira_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageGiraSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_hyado_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageHyadoSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_io_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageIoSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_jibaria_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageJibariaSpellDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_os_pattern "
      + "from kokoro_flat_cb_mv order by sage_mera_spell_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageMeraSpellDamages(final Integer limit);

  // Sage, Healing

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_ds_pattern "
      + "from kokoro_flat_cb_mv order by sage_specialty_healing desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageSpecialtyHealings(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_sage_ds_pattern "
      + "from kokoro_flat_cb_mv order by sage_spell_healing desc limit ?1",
      nativeQuery = true)
  List<Object[]> findSageSpellHealings(final Integer limit);

  // Paladin, Slash

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_bagi_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinBagiSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_dein_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinDeinSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_doruma_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinDorumaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_gira_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinGiraSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_hyado_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinHyadoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_io_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinIoSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_jibaria_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinJibariaSlashDamages(final Integer limit);

  @Query(value = "select "
      + "k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank, max_paladin_op_pattern "
      + "from kokoro_flat_cb_mv order by paladin_mera_slash_damage desc limit ?1",
      nativeQuery = true)
  List<Object[]> findPaladinMeraSlashDamages(final Integer limit);

}
