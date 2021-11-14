WITH cb AS
(
SELECT
`hirooka-pro.dqwapi.getMaxPattern`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type) as max_battle_master_op_pattern,
`hirooka-pro.dqwapi.getMaxPattern`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type) as max_battle_master_os_pattern,
`hirooka-pro.dqwapi.getMaxPattern`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type) as max_battle_master_opos_pattern,
`hirooka-pro.dqwapi.getMaxPattern`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type) as max_battle_master_opdx_pattern,
`hirooka-pro.dqwapi.getMaxPattern`('BATTLE_MASTER', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type) as max_battle_master_ds_pattern,

k0.id k0id, k0.rank k0rank, k0.type k0type, k0.hp k0hp, k0.mp k0mp, k0.op k0op, k0.dp k0dp, k0.os k0os, k0.ds k0ds, k0.dx k0dx, k0.sp k0sp, k0.cost k0cost,
k1.id k1id, k1.rank k1rank, k1.type k1type, k1.hp k1hp, k1.mp k1mp, k1.op k1op, k1.dp k1dp, k1.os k1os, k1.ds k1ds, k1.dx k1dx, k1.sp k1sp, k1.cost k1cost,
k2.id k2id, k2.rank k2rank, k2.type k2type, k2.hp k2hp, k2.mp k2mp, k2.op k2op, k2.dp k2dp, k2.os k2os, k2.ds k2ds, k2.dx k2dx, k2.sp k2sp, k2.cost k2cost,
k3.id k3id, k3.rank k3rank, k3.type k3type, k3.hp k3hp, k3.mp k3mp, k3.op k3op, k3.dp k3dp, k3.os k3os, k3.ds k3ds, k3.dx k3dx, k3.sp k3sp, k3.cost k3cost,

k0.name k0name, k1.name k1name, k2.name k2name, k3.name k3name,

k0.cost + k1.cost + k2.cost + k3.cost - k0.plusCost - k1.plusCost - k2.plusCost - k3.plusCost total_cost,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_physical_spell_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_physical_spell_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathBagiNone+k2.breathBagiNone+k3.breathBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) battle_master_bagi_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathDeinNone+k2.breathDeinNone+k3.breathDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) battle_master_dein_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathDorumaNone+k1.breathDorumaNone+k2.breathDorumaNone+k3.breathDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) battle_master_doruma_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathGiraNone+k1.breathGiraNone+k2.breathGiraNone+k3.breathGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) battle_master_gira_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathHyadoNone+k1.breathHyadoNone+k2.breathHyadoNone+k3.breathHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) battle_master_hyado_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathIoNone+k1.breathIoNone+k2.breathIoNone+k3.breathIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) battle_master_io_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathJibariaNone+k1.breathJibariaNone+k2.breathJibariaNone+k3.breathJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) battle_master_jibaria_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathMeraNone+k1.breathMeraNone+k2.breathMeraNone+k3.breathMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) battle_master_mera_breath_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpecialty+k1.healingSpecialty+k2.healingSpecialty+k3.healingSpecialty)/100.0))
) battle_master_specialty_healing,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('BATTLE_MASTER', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpell+k1.healingSpell+k2.healingSpell+k3.healingSpell)/100.0))
) battle_master_spell_healing,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_physical_spell_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_physical_spell_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathBagiNone+k2.breathBagiNone+k3.breathBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) sage_bagi_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathDeinNone+k2.breathDeinNone+k3.breathDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) sage_dein_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathDorumaNone+k1.breathDorumaNone+k2.breathDorumaNone+k3.breathDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) sage_doruma_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathGiraNone+k1.breathGiraNone+k2.breathGiraNone+k3.breathGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) sage_gira_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathHyadoNone+k1.breathHyadoNone+k2.breathHyadoNone+k3.breathHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) sage_hyado_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathIoNone+k1.breathIoNone+k2.breathIoNone+k3.breathIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) sage_io_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathJibariaNone+k1.breathJibariaNone+k2.breathJibariaNone+k3.breathJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) sage_jibaria_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathMeraNone+k1.breathMeraNone+k2.breathMeraNone+k3.breathMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) sage_mera_breath_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpecialty+k1.healingSpecialty+k2.healingSpecialty+k3.healingSpecialty)/100.0))
) sage_specialty_healing,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('SAGE', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpell+k1.healingSpell+k2.healingSpell+k3.healingSpell)/100.0))
) sage_spell_healing,


ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_physical_spell_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_physical_spell_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathBagiNone+k2.breathBagiNone+k3.breathBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) ranger_bagi_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathDeinNone+k2.breathDeinNone+k3.breathDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) ranger_dein_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathDorumaNone+k1.breathDorumaNone+k2.breathDorumaNone+k3.breathDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) ranger_doruma_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathGiraNone+k1.breathGiraNone+k2.breathGiraNone+k3.breathGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) ranger_gira_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathHyadoNone+k1.breathHyadoNone+k2.breathHyadoNone+k3.breathHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) ranger_hyado_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathIoNone+k1.breathIoNone+k2.breathIoNone+k3.breathIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) ranger_io_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathJibariaNone+k1.breathJibariaNone+k2.breathJibariaNone+k3.breathJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) ranger_jibaria_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathMeraNone+k1.breathMeraNone+k2.breathMeraNone+k3.breathMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) ranger_mera_breath_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpecialty+k1.healingSpecialty+k2.healingSpecialty+k3.healingSpecialty)/100.0))
) ranger_specialty_healing,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('RANGER', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpell+k1.healingSpell+k2.healingSpell+k3.healingSpell)/100.0))
) ranger_spell_healing,


ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op, k0.type, k1.op, k1.type, k2.op, k2.type, k3.op, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellBagiNone+k2.spellBagiNone+k3.spellBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellBagiNone+k1.spellDeinNone+k2.spellDeinNone+k3.spellDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellDorumaNone+k1.spellDorumaNone+k2.spellDorumaNone+k3.spellDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellGiraNone+k1.spellGiraNone+k2.spellGiraNone+k3.spellGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellHyadoNone+k1.spellHyadoNone+k2.spellHyadoNone+k3.spellHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellIoNone+k1.spellIoNone+k2.spellIoNone+k3.spellIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellJibariaNone+k1.spellJibariaNone+k2.spellJibariaNone+k3.spellJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_spell_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.os, k0.type, k1.os, k1.type, k2.os, k2.type, k3.os, k3.type)
  * (1+((k0.spellNoneNone+k1.spellNoneNone+k2.spellNoneNone+k3.spellNoneNone)/100.0))
  * (1+((k0.spellMeraNone+k1.spellMeraNone+k2.spellMeraNone+k3.spellMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_spell_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashBagiNone+k2.slashBagiNone+k3.slashBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashBagiNone+k1.slashDeinNone+k2.slashDeinNone+k3.slashDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashDorumaNone+k1.slashDorumaNone+k2.slashDorumaNone+k3.slashDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashGiraNone+k1.slashGiraNone+k2.slashGiraNone+k3.slashGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashHyadoNone+k1.slashHyadoNone+k2.slashHyadoNone+k3.slashHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashIoNone+k1.slashIoNone+k2.slashIoNone+k3.slashIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashJibariaNone+k1.slashJibariaNone+k2.slashJibariaNone+k3.slashJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_physical_spell_slash_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.slashNoneNone+k1.slashNoneNone+k2.slashNoneNone+k3.slashNoneNone)/100.0))
  * (1+((k0.slashMeraNone+k1.slashMeraNone+k2.slashMeraNone+k3.slashMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_physical_spell_slash_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitBagiNone+k2.hitBagiNone+k3.hitBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitBagiNone+k1.hitDeinNone+k2.hitDeinNone+k3.hitDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitDorumaNone+k1.hitDorumaNone+k2.hitDorumaNone+k3.hitDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitGiraNone+k1.hitGiraNone+k2.hitGiraNone+k3.hitGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitHyadoNone+k1.hitHyadoNone+k2.hitHyadoNone+k3.hitHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitIoNone+k1.hitIoNone+k2.hitIoNone+k3.hitIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitJibariaNone+k1.hitJibariaNone+k2.hitJibariaNone+k3.hitJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_physical_spell_hit_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.os, k0.type, k1.op+k1.os, k1.type, k2.op+k2.os, k2.type, k3.op+k3.os, k3.type)
  * (1+((k0.hitNoneNone+k1.hitNoneNone+k2.hitNoneNone+k3.hitNoneNone)/100.0))
  * (1+((k0.hitMeraNone+k1.hitMeraNone+k2.hitMeraNone+k3.hitMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_physical_spell_hit_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathBagiNone+k2.breathBagiNone+k3.breathBagiNone)/100.0))
  * (1+((k0.allBagiNone+k1.allBagiNone+k2.allBagiNone+k3.allBagiNone)/100.0))
) paladin_bagi_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathBagiNone+k1.breathDeinNone+k2.breathDeinNone+k3.breathDeinNone)/100.0))
  * (1+((k0.allDeinNone+k1.allDeinNone+k2.allDeinNone+k3.allDeinNone)/100.0))
) paladin_dein_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathDorumaNone+k1.breathDorumaNone+k2.breathDorumaNone+k3.breathDorumaNone)/100.0))
  * (1+((k0.allDorumaNone+k1.allDorumaNone+k2.allDorumaNone+k3.allDorumaNone)/100.0))
) paladin_doruma_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathGiraNone+k1.breathGiraNone+k2.breathGiraNone+k3.breathGiraNone)/100.0))
  * (1+((k0.allGiraNone+k1.allGiraNone+k2.allGiraNone+k3.allGiraNone)/100.0))
) paladin_gira_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathHyadoNone+k1.breathHyadoNone+k2.breathHyadoNone+k3.breathHyadoNone)/100.0))
  * (1+((k0.allHyadoNone+k1.allHyadoNone+k2.allHyadoNone+k3.allHyadoNone)/100.0))
) paladin_hyado_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathIoNone+k1.breathIoNone+k2.breathIoNone+k3.breathIoNone)/100.0))
  * (1+((k0.allIoNone+k1.allIoNone+k2.allIoNone+k3.allIoNone)/100.0))
) paladin_io_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathJibariaNone+k1.breathJibariaNone+k2.breathJibariaNone+k3.breathJibariaNone)/100.0))
  * (1+((k0.allJibariaNone+k1.allJibariaNone+k2.allJibariaNone+k3.allJibariaNone)/100.0))
) paladin_jibaria_breath_damage,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.op+k0.dx, k0.type, k1.op+k1.dx, k1.type, k2.op+k2.dx, k2.type, k3.op+k3.dx, k3.type)
  * (1+((k0.breathNoneNone+k1.breathNoneNone+k2.breathNoneNone+k3.breathNoneNone)/100.0))
  * (1+((k0.breathMeraNone+k1.breathMeraNone+k2.breathMeraNone+k3.breathMeraNone)/100.0))
  * (1+((k0.allMeraNone+k1.allMeraNone+k2.allMeraNone+k3.allMeraNone)/100.0))
) paladin_mera_breath_damage,

ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpecialty+k1.healingSpecialty+k2.healingSpecialty+k3.healingSpecialty)/100.0))
) paladin_specialty_healing,
ceil(
  `hirooka-pro.dqwapi.getMaxValue`('PARADIN', k0.ds, k0.type, k1.ds, k1.type, k2.ds, k2.type, k3.ds, k3.type)
  * (1+((k0.healingSkill+k1.healingSkill+k2.healingSkill+k3.healingSkill)/100.0))
  * (1+((k0.healingSpell+k1.healingSpell+k2.healingSpell+k3.healingSpell)/100.0))
) paladin_spell_healing,

FROM `hirooka-pro.dqwapi.kokoro` k0
CROSS JOIN `hirooka-pro.dqwapi.kokoro` k1
CROSS JOIN `hirooka-pro.dqwapi.kokoro` k2
CROSS JOIN `hirooka-pro.dqwapi.kokoro` k3
WHERE k0.name < k1.name AND k0.name < k2.name AND k0.name < k3.name AND k1.name < k2.name AND k1.name < k3.name AND k2.name < k3.name
)
SELECT
k0id, k0rank, k1id, k1rank, k2id, k2rank, k3id, k3rank,
$pattern
FROM cb
WHERE total_cost < $cost
AND k0id NOT IN ( $joinedNonBrides )
AND k1id NOT IN ( $joinedNonBrides )
AND k2id NOT IN ( $joinedNonBrides )
AND k3id NOT IN ( $joinedNonBrides )
AND CONCAT(k0id, '_', k0rank) NOT IN ( $joinedExclusions )
AND CONCAT(k1id, '_', k1rank) NOT IN ( $joinedExclusions )
AND CONCAT(k2id, '_', k2rank) NOT IN ( $joinedExclusions )
AND CONCAT(k3id, '_', k3rank) NOT IN ( $joinedExclusions )
ORDER BY $column DESC limit $limit
