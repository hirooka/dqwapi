```
drop table if exists kokoro_combination;
create table kokoro_combination (
  k0id integer,
  k0rank integer,
  k1id integer,
  k1rank integer,
  k2id integer,
  k2rank integer,
  k3id integer,
  k3rank integer,

  /* -------- BATTLE_MASTER -------- */
  max_battle_master_op_pattern text,
  max_battle_master_os_pattern text,
  max_battle_master_opos_pattern text,
  max_battle_master_opdx_pattern text,
  max_battle_master_ds_pattern text,
  
  battle_master_bagi_slash_damage integer,
  battle_master_dein_slash_damage integer,
  battle_master_doruma_slash_damage integer,
  battle_master_gira_slash_damage integer,
  battle_master_hyado_slash_damage integer,
  battle_master_io_slash_damage integer,
  battle_master_jibaria_slash_damage integer,
  battle_master_mera_slash_damage integer,
  
  battle_master_bagi_hit_damage integer,
  battle_master_dein_hit_damage integer,
  battle_master_doruma_hit_damage integer,
  battle_master_gira_hit_damage integer,
  battle_master_hyado_hit_damage integer,
  battle_master_io_hit_damage integer,
  battle_master_jibaria_hit_damage integer,
  battle_master_mera_hit_damage integer,
  
  battle_master_bagi_spell_damage integer,
  battle_master_dein_spell_damage integer,
  battle_master_doruma_spell_damage integer,
  battle_master_gira_spell_damage integer,
  battle_master_hyado_spell_damage integer,
  battle_master_io_spell_damage integer,
  battle_master_jibaria_spell_damage integer,
  battle_master_mera_spell_damage integer,
  
  battle_master_bagi_slash_physical_spell_damage integer,
  battle_master_dein_slash_physical_spell_damage integer,
  battle_master_doruma_slash_physical_spell_damage integer,
  battle_master_gira_slash_physical_spell_damage integer,
  battle_master_hyado_slash_physical_spell_damage integer,
  battle_master_io_slash_physical_spell_damage integer,
  battle_master_jibaria_slash_physical_spell_damage integer,
  battle_master_mera_slash_physical_spell_damage integer,
  
  battle_master_bagi_hit_physical_spell_damage integer,
  battle_master_dein_hit_physical_spell_damage integer,
  battle_master_doruma_hit_physical_spell_damage integer,
  battle_master_gira_hit_physical_spell_damage integer,
  battle_master_hyado_hit_physical_spell_damage integer,
  battle_master_io_hit_physical_spell_damage integer,
  battle_master_jibaria_hit_physical_spell_damage integer,
  battle_master_mera_hit_physical_spell_damage integer,

  battle_master_bagi_breath_damage integer,
  battle_master_dein_breath_damage integer,
  battle_master_doruma_breath_damage integer,
  battle_master_gira_breath_damage integer,
  battle_master_hyado_breath_damage integer,
  battle_master_io_breath_damage integer,
  battle_master_jibaria_breath_damage integer,
  battle_master_mera_breath_damage integer,
  
  battle_master_speciality_healing integer,
  battle_master_spell_healing integer,

  /* -------- SAGE -------- */
  max_sage_op_pattern text,
  max_sage_os_pattern text,
  max_sage_opos_pattern text,
  max_sage_opdx_pattern text,
  max_sage_ds_pattern text,

  sage_bagi_slash_damage integer,
  sage_dein_slash_damage integer,
  sage_doruma_slash_damage integer,
  sage_gira_slash_damage integer,
  sage_hyado_slash_damage integer,
  sage_io_slash_damage integer,
  sage_jibaria_slash_damage integer,
  sage_mera_slash_damage integer,

  sage_bagi_hit_damage integer,
  sage_dein_hit_damage integer,
  sage_doruma_hit_damage integer,
  sage_gira_hit_damage integer,
  sage_hyado_hit_damage integer,
  sage_io_hit_damage integer,
  sage_jibaria_hit_damage integer,
  sage_mera_hit_damage integer,

  sage_bagi_spell_damage integer,
  sage_dein_spell_damage integer,
  sage_doruma_spell_damage integer,
  sage_gira_spell_damage integer,
  sage_hyado_spell_damage integer,
  sage_io_spell_damage integer,
  sage_jibaria_spell_damage integer,
  sage_mera_spell_damage integer,

  sage_bagi_slash_physical_spell_damage integer,
  sage_dein_slash_physical_spell_damage integer,
  sage_doruma_slash_physical_spell_damage integer,
  sage_gira_slash_physical_spell_damage integer,
  sage_hyado_slash_physical_spell_damage integer,
  sage_io_slash_physical_spell_damage integer,
  sage_jibaria_slash_physical_spell_damage integer,
  sage_mera_slash_physical_spell_damage integer,

  sage_bagi_hit_physical_spell_damage integer,
  sage_dein_hit_physical_spell_damage integer,
  sage_doruma_hit_physical_spell_damage integer,
  sage_gira_hit_physical_spell_damage integer,
  sage_hyado_hit_physical_spell_damage integer,
  sage_io_hit_physical_spell_damage integer,
  sage_jibaria_hit_physical_spell_damage integer,
  sage_mera_hit_physical_spell_damage integer,

  sage_bagi_breath_damage integer,
  sage_dein_breath_damage integer,
  sage_doruma_breath_damage integer,
  sage_gira_breath_damage integer,
  sage_hyado_breath_damage integer,
  sage_io_breath_damage integer,
  sage_jibaria_breath_damage integer,
  sage_mera_breath_damage integer,

  sage_speciality_healing integer,
  sage_spell_healing integer,
  
  /* -------- RANGER -------- */
  max_ranger_op_pattern text,
  max_ranger_os_pattern text,
  max_ranger_opos_pattern text,
  max_ranger_opdx_pattern text,
  max_ranger_ds_pattern text,

  ranger_bagi_slash_damage integer,
  ranger_dein_slash_damage integer,
  ranger_doruma_slash_damage integer,
  ranger_gira_slash_damage integer,
  ranger_hyado_slash_damage integer,
  ranger_io_slash_damage integer,
  ranger_jibaria_slash_damage integer,
  ranger_mera_slash_damage integer,

  ranger_bagi_hit_damage integer,
  ranger_dein_hit_damage integer,
  ranger_doruma_hit_damage integer,
  ranger_gira_hit_damage integer,
  ranger_hyado_hit_damage integer,
  ranger_io_hit_damage integer,
  ranger_jibaria_hit_damage integer,
  ranger_mera_hit_damage integer,

  ranger_bagi_spell_damage integer,
  ranger_dein_spell_damage integer,
  ranger_doruma_spell_damage integer,
  ranger_gira_spell_damage integer,
  ranger_hyado_spell_damage integer,
  ranger_io_spell_damage integer,
  ranger_jibaria_spell_damage integer,
  ranger_mera_spell_damage integer,

  ranger_bagi_slash_physical_spell_damage integer,
  ranger_dein_slash_physical_spell_damage integer,
  ranger_doruma_slash_physical_spell_damage integer,
  ranger_gira_slash_physical_spell_damage integer,
  ranger_hyado_slash_physical_spell_damage integer,
  ranger_io_slash_physical_spell_damage integer,
  ranger_jibaria_slash_physical_spell_damage integer,
  ranger_mera_slash_physical_spell_damage integer,

  ranger_bagi_hit_physical_spell_damage integer,
  ranger_dein_hit_physical_spell_damage integer,
  ranger_doruma_hit_physical_spell_damage integer,
  ranger_gira_hit_physical_spell_damage integer,
  ranger_hyado_hit_physical_spell_damage integer,
  ranger_io_hit_physical_spell_damage integer,
  ranger_jibaria_hit_physical_spell_damage integer,
  ranger_mera_hit_physical_spell_damage integer,

  ranger_bagi_breath_damage integer,
  ranger_dein_breath_damage integer,
  ranger_doruma_breath_damage integer,
  ranger_gira_breath_damage integer,
  ranger_hyado_breath_damage integer,
  ranger_io_breath_damage integer,
  ranger_jibaria_breath_damage integer,
  ranger_mera_breath_damage integer,

  ranger_speciality_healing integer,
  ranger_spell_healing integer,

  /* -------- ARMAMENTALIST -------- */
  max_armamentalist_op_pattern text,
  max_armamentalist_os_pattern text,
  max_armamentalist_opos_pattern text,
  max_armamentalist_opdx_pattern text,
  max_armamentalist_ds_pattern text,

  armamentalist_bagi_slash_damage integer,
  armamentalist_dein_slash_damage integer,
  armamentalist_doruma_slash_damage integer,
  armamentalist_gira_slash_damage integer,
  armamentalist_hyado_slash_damage integer,
  armamentalist_io_slash_damage integer,
  armamentalist_jibaria_slash_damage integer,
  armamentalist_mera_slash_damage integer,

  armamentalist_bagi_hit_damage integer,
  armamentalist_dein_hit_damage integer,
  armamentalist_doruma_hit_damage integer,
  armamentalist_gira_hit_damage integer,
  armamentalist_hyado_hit_damage integer,
  armamentalist_io_hit_damage integer,
  armamentalist_jibaria_hit_damage integer,
  armamentalist_mera_hit_damage integer,

  armamentalist_bagi_spell_damage integer,
  armamentalist_dein_spell_damage integer,
  armamentalist_doruma_spell_damage integer,
  armamentalist_gira_spell_damage integer,
  armamentalist_hyado_spell_damage integer,
  armamentalist_io_spell_damage integer,
  armamentalist_jibaria_spell_damage integer,
  armamentalist_mera_spell_damage integer,

  armamentalist_bagi_slash_physical_spell_damage integer,
  armamentalist_dein_slash_physical_spell_damage integer,
  armamentalist_doruma_slash_physical_spell_damage integer,
  armamentalist_gira_slash_physical_spell_damage integer,
  armamentalist_hyado_slash_physical_spell_damage integer,
  armamentalist_io_slash_physical_spell_damage integer,
  armamentalist_jibaria_slash_physical_spell_damage integer,
  armamentalist_mera_slash_physical_spell_damage integer,

  armamentalist_bagi_hit_physical_spell_damage integer,
  armamentalist_dein_hit_physical_spell_damage integer,
  armamentalist_doruma_hit_physical_spell_damage integer,
  armamentalist_gira_hit_physical_spell_damage integer,
  armamentalist_hyado_hit_physical_spell_damage integer,
  armamentalist_io_hit_physical_spell_damage integer,
  armamentalist_jibaria_hit_physical_spell_damage integer,
  armamentalist_mera_hit_physical_spell_damage integer,

  armamentalist_bagi_breath_damage integer,
  armamentalist_dein_breath_damage integer,
  armamentalist_doruma_breath_damage integer,
  armamentalist_gira_breath_damage integer,
  armamentalist_hyado_breath_damage integer,
  armamentalist_io_breath_damage integer,
  armamentalist_jibaria_breath_damage integer,
  armamentalist_mera_breath_damage integer,

  armamentalist_speciality_healing integer,
  armamentalist_spell_healing integer,

  /* -------- PALADIN -------- */
  max_paladin_op_pattern text,
  max_paladin_os_pattern text,
  max_paladin_opos_pattern text,
  max_paladin_opdx_pattern text,
  max_paladin_ds_pattern text,

  paladin_bagi_slash_damage integer,
  paladin_dein_slash_damage integer,
  paladin_doruma_slash_damage integer,
  paladin_gira_slash_damage integer,
  paladin_hyado_slash_damage integer,
  paladin_io_slash_damage integer,
  paladin_jibaria_slash_damage integer,
  paladin_mera_slash_damage integer,

  paladin_bagi_hit_damage integer,
  paladin_dein_hit_damage integer,
  paladin_doruma_hit_damage integer,
  paladin_gira_hit_damage integer,
  paladin_hyado_hit_damage integer,
  paladin_io_hit_damage integer,
  paladin_jibaria_hit_damage integer,
  paladin_mera_hit_damage integer,

  paladin_bagi_spell_damage integer,
  paladin_dein_spell_damage integer,
  paladin_doruma_spell_damage integer,
  paladin_gira_spell_damage integer,
  paladin_hyado_spell_damage integer,
  paladin_io_spell_damage integer,
  paladin_jibaria_spell_damage integer,
  paladin_mera_spell_damage integer,

  paladin_bagi_slash_physical_spell_damage integer,
  paladin_dein_slash_physical_spell_damage integer,
  paladin_doruma_slash_physical_spell_damage integer,
  paladin_gira_slash_physical_spell_damage integer,
  paladin_hyado_slash_physical_spell_damage integer,
  paladin_io_slash_physical_spell_damage integer,
  paladin_jibaria_slash_physical_spell_damage integer,
  paladin_mera_slash_physical_spell_damage integer,

  paladin_bagi_hit_physical_spell_damage integer,
  paladin_dein_hit_physical_spell_damage integer,
  paladin_doruma_hit_physical_spell_damage integer,
  paladin_gira_hit_physical_spell_damage integer,
  paladin_hyado_hit_physical_spell_damage integer,
  paladin_io_hit_physical_spell_damage integer,
  paladin_jibaria_hit_physical_spell_damage integer,
  paladin_mera_hit_physical_spell_damage integer,

  paladin_bagi_breath_damage integer,
  paladin_dein_breath_damage integer,
  paladin_doruma_breath_damage integer,
  paladin_gira_breath_damage integer,
  paladin_hyado_breath_damage integer,
  paladin_io_breath_damage integer,
  paladin_jibaria_breath_damage integer,
  paladin_mera_breath_damage integer,

  paladin_speciality_healing integer,
  paladin_spell_healing integer,

  /* -------- SUPERSTAR -------- */
  max_superstar_op_pattern text,
  max_superstar_os_pattern text,
  max_superstar_opos_pattern text,
  max_superstar_opdx_pattern text,
  max_superstar_ds_pattern text,

  superstar_bagi_slash_damage integer,
  superstar_dein_slash_damage integer,
  superstar_doruma_slash_damage integer,
  superstar_gira_slash_damage integer,
  superstar_hyado_slash_damage integer,
  superstar_io_slash_damage integer,
  superstar_jibaria_slash_damage integer,
  superstar_mera_slash_damage integer,

  superstar_bagi_hit_damage integer,
  superstar_dein_hit_damage integer,
  superstar_doruma_hit_damage integer,
  superstar_gira_hit_damage integer,
  superstar_hyado_hit_damage integer,
  superstar_io_hit_damage integer,
  superstar_jibaria_hit_damage integer,
  superstar_mera_hit_damage integer,

  superstar_bagi_spell_damage integer,
  superstar_dein_spell_damage integer,
  superstar_doruma_spell_damage integer,
  superstar_gira_spell_damage integer,
  superstar_hyado_spell_damage integer,
  superstar_io_spell_damage integer,
  superstar_jibaria_spell_damage integer,
  superstar_mera_spell_damage integer,

  superstar_bagi_slash_physical_spell_damage integer,
  superstar_dein_slash_physical_spell_damage integer,
  superstar_doruma_slash_physical_spell_damage integer,
  superstar_gira_slash_physical_spell_damage integer,
  superstar_hyado_slash_physical_spell_damage integer,
  superstar_io_slash_physical_spell_damage integer,
  superstar_jibaria_slash_physical_spell_damage integer,
  superstar_mera_slash_physical_spell_damage integer,

  superstar_bagi_hit_physical_spell_damage integer,
  superstar_dein_hit_physical_spell_damage integer,
  superstar_doruma_hit_physical_spell_damage integer,
  superstar_gira_hit_physical_spell_damage integer,
  superstar_hyado_hit_physical_spell_damage integer,
  superstar_io_hit_physical_spell_damage integer,
  superstar_jibaria_hit_physical_spell_damage integer,
  superstar_mera_hit_physical_spell_damage integer,

  superstar_bagi_breath_damage integer,
  superstar_dein_breath_damage integer,
  superstar_doruma_breath_damage integer,
  superstar_gira_breath_damage integer,
  superstar_hyado_breath_damage integer,
  superstar_io_breath_damage integer,
  superstar_jibaria_breath_damage integer,
  superstar_mera_breath_damage integer,

  superstar_speciality_healing integer,
  superstar_spell_healing integer,

  /* -------- PIRATE -------- */
  max_pirate_op_pattern text,
  max_pirate_os_pattern text,
  max_pirate_opos_pattern text,
  max_pirate_opdx_pattern text,
  max_pirate_ds_pattern text,

  pirate_bagi_slash_damage integer,
  pirate_dein_slash_damage integer,
  pirate_doruma_slash_damage integer,
  pirate_gira_slash_damage integer,
  pirate_hyado_slash_damage integer,
  pirate_io_slash_damage integer,
  pirate_jibaria_slash_damage integer,
  pirate_mera_slash_damage integer,

  pirate_bagi_hit_damage integer,
  pirate_dein_hit_damage integer,
  pirate_doruma_hit_damage integer,
  pirate_gira_hit_damage integer,
  pirate_hyado_hit_damage integer,
  pirate_io_hit_damage integer,
  pirate_jibaria_hit_damage integer,
  pirate_mera_hit_damage integer,

  pirate_bagi_spell_damage integer,
  pirate_dein_spell_damage integer,
  pirate_doruma_spell_damage integer,
  pirate_gira_spell_damage integer,
  pirate_hyado_spell_damage integer,
  pirate_io_spell_damage integer,
  pirate_jibaria_spell_damage integer,
  pirate_mera_spell_damage integer,

  pirate_bagi_slash_physical_spell_damage integer,
  pirate_dein_slash_physical_spell_damage integer,
  pirate_doruma_slash_physical_spell_damage integer,
  pirate_gira_slash_physical_spell_damage integer,
  pirate_hyado_slash_physical_spell_damage integer,
  pirate_io_slash_physical_spell_damage integer,
  pirate_jibaria_slash_physical_spell_damage integer,
  pirate_mera_slash_physical_spell_damage integer,

  pirate_bagi_hit_physical_spell_damage integer,
  pirate_dein_hit_physical_spell_damage integer,
  pirate_doruma_hit_physical_spell_damage integer,
  pirate_gira_hit_physical_spell_damage integer,
  pirate_hyado_hit_physical_spell_damage integer,
  pirate_io_hit_physical_spell_damage integer,
  pirate_jibaria_hit_physical_spell_damage integer,
  pirate_mera_hit_physical_spell_damage integer,

  pirate_bagi_breath_damage integer,
  pirate_dein_breath_damage integer,
  pirate_doruma_breath_damage integer,
  pirate_gira_breath_damage integer,
  pirate_hyado_breath_damage integer,
  pirate_io_breath_damage integer,
  pirate_jibaria_breath_damage integer,
  pirate_mera_breath_damage integer,

  pirate_speciality_healing integer,
  pirate_spell_healing integer,
  
  total_cost integer
);
```

```
/* -------- BATTLE_MASTER -------- */
create index on kokoro_combination(battle_master_bagi_slash_damage);
create index on kokoro_combination(battle_master_dein_slash_damage);
create index on kokoro_combination(battle_master_doruma_slash_damage);
create index on kokoro_combination(battle_master_gira_slash_damage);
create index on kokoro_combination(battle_master_hyado_slash_damage);
create index on kokoro_combination(battle_master_io_slash_damage);
create index on kokoro_combination(battle_master_jibaria_slash_damage);
create index on kokoro_combination(battle_master_mera_slash_damage);
  
create index on kokoro_combination(battle_master_bagi_hit_damage);
create index on kokoro_combination(battle_master_dein_hit_damage);
create index on kokoro_combination(battle_master_doruma_hit_damage);
create index on kokoro_combination(battle_master_gira_hit_damage);
create index on kokoro_combination(battle_master_hyado_hit_damage);
create index on kokoro_combination(battle_master_io_hit_damage);
create index on kokoro_combination(battle_master_jibaria_hit_damage);
create index on kokoro_combination(battle_master_mera_hit_damage);

create index on kokoro_combination(battle_master_bagi_spell_damage);
create index on kokoro_combination(battle_master_dein_spell_damage);
create index on kokoro_combination(battle_master_doruma_spell_damage);
create index on kokoro_combination(battle_master_gira_spell_damage);
create index on kokoro_combination(battle_master_hyado_spell_damage);
create index on kokoro_combination(battle_master_io_spell_damage);
create index on kokoro_combination(battle_master_jibaria_spell_damage);
create index on kokoro_combination(battle_master_mera_spell_damage);

create index on kokoro_combination(battle_master_bagi_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_dein_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_doruma_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_gira_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_hyado_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_io_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(battle_master_mera_slash_physical_spell_damage);

create index on kokoro_combination(battle_master_bagi_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_dein_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_doruma_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_gira_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_hyado_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_io_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(battle_master_mera_hit_physical_spell_damage);

create index on kokoro_combination(battle_master_bagi_breath_damage);
create index on kokoro_combination(battle_master_dein_breath_damage);
create index on kokoro_combination(battle_master_doruma_breath_damage);
create index on kokoro_combination(battle_master_gira_breath_damage);
create index on kokoro_combination(battle_master_hyado_breath_damage);
create index on kokoro_combination(battle_master_io_breath_damage);
create index on kokoro_combination(battle_master_jibaria_breath_damage);
create index on kokoro_combination(battle_master_mera_breath_damage);

create index on kokoro_combination(battle_master_speciality_healing);
create index on kokoro_combination(battle_master_spell_healing);

/* -------- SAGE -------- */
create index on kokoro_combination(sage_bagi_slash_damage);
create index on kokoro_combination(sage_dein_slash_damage);
create index on kokoro_combination(sage_doruma_slash_damage);
create index on kokoro_combination(sage_gira_slash_damage);
create index on kokoro_combination(sage_hyado_slash_damage);
create index on kokoro_combination(sage_io_slash_damage);
create index on kokoro_combination(sage_jibaria_slash_damage);
create index on kokoro_combination(sage_mera_slash_damage);

create index on kokoro_combination(sage_bagi_hit_damage);
create index on kokoro_combination(sage_dein_hit_damage);
create index on kokoro_combination(sage_doruma_hit_damage);
create index on kokoro_combination(sage_gira_hit_damage);
create index on kokoro_combination(sage_hyado_hit_damage);
create index on kokoro_combination(sage_io_hit_damage);
create index on kokoro_combination(sage_jibaria_hit_damage);
create index on kokoro_combination(sage_mera_hit_damage);

create index on kokoro_combination(sage_bagi_spell_damage);
create index on kokoro_combination(sage_dein_spell_damage);
create index on kokoro_combination(sage_doruma_spell_damage);
create index on kokoro_combination(sage_gira_spell_damage);
create index on kokoro_combination(sage_hyado_spell_damage);
create index on kokoro_combination(sage_io_spell_damage);
create index on kokoro_combination(sage_jibaria_spell_damage);
create index on kokoro_combination(sage_mera_spell_damage);

create index on kokoro_combination(sage_bagi_slash_physical_spell_damage);
create index on kokoro_combination(sage_dein_slash_physical_spell_damage);
create index on kokoro_combination(sage_doruma_slash_physical_spell_damage);
create index on kokoro_combination(sage_gira_slash_physical_spell_damage);
create index on kokoro_combination(sage_hyado_slash_physical_spell_damage);
create index on kokoro_combination(sage_io_slash_physical_spell_damage);
create index on kokoro_combination(sage_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(sage_mera_slash_physical_spell_damage);

create index on kokoro_combination(sage_bagi_hit_physical_spell_damage);
create index on kokoro_combination(sage_dein_hit_physical_spell_damage);
create index on kokoro_combination(sage_doruma_hit_physical_spell_damage);
create index on kokoro_combination(sage_gira_hit_physical_spell_damage);
create index on kokoro_combination(sage_hyado_hit_physical_spell_damage);
create index on kokoro_combination(sage_io_hit_physical_spell_damage);
create index on kokoro_combination(sage_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(sage_mera_hit_physical_spell_damage);

create index on kokoro_combination(sage_bagi_breath_damage);
create index on kokoro_combination(sage_dein_breath_damage);
create index on kokoro_combination(sage_doruma_breath_damage);
create index on kokoro_combination(sage_gira_breath_damage);
create index on kokoro_combination(sage_hyado_breath_damage);
create index on kokoro_combination(sage_io_breath_damage);
create index on kokoro_combination(sage_jibaria_breath_damage);
create index on kokoro_combination(sage_mera_breath_damage);

create index on kokoro_combination(sage_speciality_healing);
create index on kokoro_combination(sage_spell_healing);

/* -------- RANGER -------- */
create index on kokoro_combination(ranger_bagi_slash_damage);
create index on kokoro_combination(ranger_dein_slash_damage);
create index on kokoro_combination(ranger_doruma_slash_damage);
create index on kokoro_combination(ranger_gira_slash_damage);
create index on kokoro_combination(ranger_hyado_slash_damage);
create index on kokoro_combination(ranger_io_slash_damage);
create index on kokoro_combination(ranger_jibaria_slash_damage);
create index on kokoro_combination(ranger_mera_slash_damage);

create index on kokoro_combination(ranger_bagi_hit_damage);
create index on kokoro_combination(ranger_dein_hit_damage);
create index on kokoro_combination(ranger_doruma_hit_damage);
create index on kokoro_combination(ranger_gira_hit_damage);
create index on kokoro_combination(ranger_hyado_hit_damage);
create index on kokoro_combination(ranger_io_hit_damage);
create index on kokoro_combination(ranger_jibaria_hit_damage);
create index on kokoro_combination(ranger_mera_hit_damage);

create index on kokoro_combination(ranger_bagi_spell_damage);
create index on kokoro_combination(ranger_dein_spell_damage);
create index on kokoro_combination(ranger_doruma_spell_damage);
create index on kokoro_combination(ranger_gira_spell_damage);
create index on kokoro_combination(ranger_hyado_spell_damage);
create index on kokoro_combination(ranger_io_spell_damage);
create index on kokoro_combination(ranger_jibaria_spell_damage);
create index on kokoro_combination(ranger_mera_spell_damage);

create index on kokoro_combination(ranger_bagi_slash_physical_spell_damage);
create index on kokoro_combination(ranger_dein_slash_physical_spell_damage);
create index on kokoro_combination(ranger_doruma_slash_physical_spell_damage);
create index on kokoro_combination(ranger_gira_slash_physical_spell_damage);
create index on kokoro_combination(ranger_hyado_slash_physical_spell_damage);
create index on kokoro_combination(ranger_io_slash_physical_spell_damage);
create index on kokoro_combination(ranger_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(ranger_mera_slash_physical_spell_damage);

create index on kokoro_combination(ranger_bagi_hit_physical_spell_damage);
create index on kokoro_combination(ranger_dein_hit_physical_spell_damage);
create index on kokoro_combination(ranger_doruma_hit_physical_spell_damage);
create index on kokoro_combination(ranger_gira_hit_physical_spell_damage);
create index on kokoro_combination(ranger_hyado_hit_physical_spell_damage);
create index on kokoro_combination(ranger_io_hit_physical_spell_damage);
create index on kokoro_combination(ranger_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(ranger_mera_hit_physical_spell_damage);

create index on kokoro_combination(ranger_bagi_breath_damage);
create index on kokoro_combination(ranger_dein_breath_damage);
create index on kokoro_combination(ranger_doruma_breath_damage);
create index on kokoro_combination(ranger_gira_breath_damage);
create index on kokoro_combination(ranger_hyado_breath_damage);
create index on kokoro_combination(ranger_io_breath_damage);
create index on kokoro_combination(ranger_jibaria_breath_damage);
create index on kokoro_combination(ranger_mera_breath_damage);

create index on kokoro_combination(ranger_speciality_healing);
create index on kokoro_combination(ranger_spell_healing);

/* -------- ARMAMENTALIST -------- */
create index on kokoro_combination(armamentalist_bagi_slash_damage);
create index on kokoro_combination(armamentalist_dein_slash_damage);
create index on kokoro_combination(armamentalist_doruma_slash_damage);
create index on kokoro_combination(armamentalist_gira_slash_damage);
create index on kokoro_combination(armamentalist_hyado_slash_damage);
create index on kokoro_combination(armamentalist_io_slash_damage);
create index on kokoro_combination(armamentalist_jibaria_slash_damage);
create index on kokoro_combination(armamentalist_mera_slash_damage);

create index on kokoro_combination(armamentalist_bagi_hit_damage);
create index on kokoro_combination(armamentalist_dein_hit_damage);
create index on kokoro_combination(armamentalist_doruma_hit_damage);
create index on kokoro_combination(armamentalist_gira_hit_damage);
create index on kokoro_combination(armamentalist_hyado_hit_damage);
create index on kokoro_combination(armamentalist_io_hit_damage);
create index on kokoro_combination(armamentalist_jibaria_hit_damage);
create index on kokoro_combination(armamentalist_mera_hit_damage);

create index on kokoro_combination(armamentalist_bagi_spell_damage);
create index on kokoro_combination(armamentalist_dein_spell_damage);
create index on kokoro_combination(armamentalist_doruma_spell_damage);
create index on kokoro_combination(armamentalist_gira_spell_damage);
create index on kokoro_combination(armamentalist_hyado_spell_damage);
create index on kokoro_combination(armamentalist_io_spell_damage);
create index on kokoro_combination(armamentalist_jibaria_spell_damage);
create index on kokoro_combination(armamentalist_mera_spell_damage);

create index on kokoro_combination(armamentalist_bagi_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_dein_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_doruma_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_gira_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_hyado_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_io_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(armamentalist_mera_slash_physical_spell_damage);

create index on kokoro_combination(armamentalist_bagi_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_dein_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_doruma_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_gira_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_hyado_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_io_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(armamentalist_mera_hit_physical_spell_damage);

create index on kokoro_combination(armamentalist_bagi_breath_damage);
create index on kokoro_combination(armamentalist_dein_breath_damage);
create index on kokoro_combination(armamentalist_doruma_breath_damage);
create index on kokoro_combination(armamentalist_gira_breath_damage);
create index on kokoro_combination(armamentalist_hyado_breath_damage);
create index on kokoro_combination(armamentalist_io_breath_damage);
create index on kokoro_combination(armamentalist_jibaria_breath_damage);
create index on kokoro_combination(armamentalist_mera_breath_damage);

create index on kokoro_combination(armamentalist_speciality_healing);
create index on kokoro_combination(armamentalist_spell_healing);

/* -------- PALADIN -------- */
create index on kokoro_combination(paladin_bagi_slash_damage);
create index on kokoro_combination(paladin_dein_slash_damage);
create index on kokoro_combination(paladin_doruma_slash_damage);
create index on kokoro_combination(paladin_gira_slash_damage);
create index on kokoro_combination(paladin_hyado_slash_damage);
create index on kokoro_combination(paladin_io_slash_damage);
create index on kokoro_combination(paladin_jibaria_slash_damage);
create index on kokoro_combination(paladin_mera_slash_damage);

create index on kokoro_combination(paladin_bagi_hit_damage);
create index on kokoro_combination(paladin_dein_hit_damage);
create index on kokoro_combination(paladin_doruma_hit_damage);
create index on kokoro_combination(paladin_gira_hit_damage);
create index on kokoro_combination(paladin_hyado_hit_damage);
create index on kokoro_combination(paladin_io_hit_damage);
create index on kokoro_combination(paladin_jibaria_hit_damage);
create index on kokoro_combination(paladin_mera_hit_damage);

create index on kokoro_combination(paladin_bagi_spell_damage);
create index on kokoro_combination(paladin_dein_spell_damage);
create index on kokoro_combination(paladin_doruma_spell_damage);
create index on kokoro_combination(paladin_gira_spell_damage);
create index on kokoro_combination(paladin_hyado_spell_damage);
create index on kokoro_combination(paladin_io_spell_damage);
create index on kokoro_combination(paladin_jibaria_spell_damage);
create index on kokoro_combination(paladin_mera_spell_damage);

create index on kokoro_combination(paladin_bagi_slash_physical_spell_damage);
create index on kokoro_combination(paladin_dein_slash_physical_spell_damage);
create index on kokoro_combination(paladin_doruma_slash_physical_spell_damage);
create index on kokoro_combination(paladin_gira_slash_physical_spell_damage);
create index on kokoro_combination(paladin_hyado_slash_physical_spell_damage);
create index on kokoro_combination(paladin_io_slash_physical_spell_damage);
create index on kokoro_combination(paladin_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(paladin_mera_slash_physical_spell_damage);

create index on kokoro_combination(paladin_bagi_hit_physical_spell_damage);
create index on kokoro_combination(paladin_dein_hit_physical_spell_damage);
create index on kokoro_combination(paladin_doruma_hit_physical_spell_damage);
create index on kokoro_combination(paladin_gira_hit_physical_spell_damage);
create index on kokoro_combination(paladin_hyado_hit_physical_spell_damage);
create index on kokoro_combination(paladin_io_hit_physical_spell_damage);
create index on kokoro_combination(paladin_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(paladin_mera_hit_physical_spell_damage);

create index on kokoro_combination(paladin_bagi_breath_damage);
create index on kokoro_combination(paladin_dein_breath_damage);
create index on kokoro_combination(paladin_doruma_breath_damage);
create index on kokoro_combination(paladin_gira_breath_damage);
create index on kokoro_combination(paladin_hyado_breath_damage);
create index on kokoro_combination(paladin_io_breath_damage);
create index on kokoro_combination(paladin_jibaria_breath_damage);
create index on kokoro_combination(paladin_mera_breath_damage);

create index on kokoro_combination(paladin_speciality_healing);
create index on kokoro_combination(paladin_spell_healing);

/* -------- SUPERSTAR -------- */
create index on kokoro_combination(superstar_bagi_slash_damage);
create index on kokoro_combination(superstar_dein_slash_damage);
create index on kokoro_combination(superstar_doruma_slash_damage);
create index on kokoro_combination(superstar_gira_slash_damage);
create index on kokoro_combination(superstar_hyado_slash_damage);
create index on kokoro_combination(superstar_io_slash_damage);
create index on kokoro_combination(superstar_jibaria_slash_damage);
create index on kokoro_combination(superstar_mera_slash_damage);

create index on kokoro_combination(superstar_bagi_hit_damage);
create index on kokoro_combination(superstar_dein_hit_damage);
create index on kokoro_combination(superstar_doruma_hit_damage);
create index on kokoro_combination(superstar_gira_hit_damage);
create index on kokoro_combination(superstar_hyado_hit_damage);
create index on kokoro_combination(superstar_io_hit_damage);
create index on kokoro_combination(superstar_jibaria_hit_damage);
create index on kokoro_combination(superstar_mera_hit_damage);

create index on kokoro_combination(superstar_bagi_spell_damage);
create index on kokoro_combination(superstar_dein_spell_damage);
create index on kokoro_combination(superstar_doruma_spell_damage);
create index on kokoro_combination(superstar_gira_spell_damage);
create index on kokoro_combination(superstar_hyado_spell_damage);
create index on kokoro_combination(superstar_io_spell_damage);
create index on kokoro_combination(superstar_jibaria_spell_damage);
create index on kokoro_combination(superstar_mera_spell_damage);

create index on kokoro_combination(superstar_bagi_slash_physical_spell_damage);
create index on kokoro_combination(superstar_dein_slash_physical_spell_damage);
create index on kokoro_combination(superstar_doruma_slash_physical_spell_damage);
create index on kokoro_combination(superstar_gira_slash_physical_spell_damage);
create index on kokoro_combination(superstar_hyado_slash_physical_spell_damage);
create index on kokoro_combination(superstar_io_slash_physical_spell_damage);
create index on kokoro_combination(superstar_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(superstar_mera_slash_physical_spell_damage);

create index on kokoro_combination(superstar_bagi_hit_physical_spell_damage);
create index on kokoro_combination(superstar_dein_hit_physical_spell_damage);
create index on kokoro_combination(superstar_doruma_hit_physical_spell_damage);
create index on kokoro_combination(superstar_gira_hit_physical_spell_damage);
create index on kokoro_combination(superstar_hyado_hit_physical_spell_damage);
create index on kokoro_combination(superstar_io_hit_physical_spell_damage);
create index on kokoro_combination(superstar_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(superstar_mera_hit_physical_spell_damage);

create index on kokoro_combination(superstar_bagi_breath_damage);
create index on kokoro_combination(superstar_dein_breath_damage);
create index on kokoro_combination(superstar_doruma_breath_damage);
create index on kokoro_combination(superstar_gira_breath_damage);
create index on kokoro_combination(superstar_hyado_breath_damage);
create index on kokoro_combination(superstar_io_breath_damage);
create index on kokoro_combination(superstar_jibaria_breath_damage);
create index on kokoro_combination(superstar_mera_breath_damage);

create index on kokoro_combination(superstar_speciality_healing);
create index on kokoro_combination(superstar_spell_healing);

/* -------- PIRATE -------- */
create index on kokoro_combination(pirate_bagi_slash_damage);
create index on kokoro_combination(pirate_dein_slash_damage);
create index on kokoro_combination(pirate_doruma_slash_damage);
create index on kokoro_combination(pirate_gira_slash_damage);
create index on kokoro_combination(pirate_hyado_slash_damage);
create index on kokoro_combination(pirate_io_slash_damage);
create index on kokoro_combination(pirate_jibaria_slash_damage);
create index on kokoro_combination(pirate_mera_slash_damage);

create index on kokoro_combination(pirate_bagi_hit_damage);
create index on kokoro_combination(pirate_dein_hit_damage);
create index on kokoro_combination(pirate_doruma_hit_damage);
create index on kokoro_combination(pirate_gira_hit_damage);
create index on kokoro_combination(pirate_hyado_hit_damage);
create index on kokoro_combination(pirate_io_hit_damage);
create index on kokoro_combination(pirate_jibaria_hit_damage);
create index on kokoro_combination(pirate_mera_hit_damage);

create index on kokoro_combination(pirate_bagi_spell_damage);
create index on kokoro_combination(pirate_dein_spell_damage);
create index on kokoro_combination(pirate_doruma_spell_damage);
create index on kokoro_combination(pirate_gira_spell_damage);
create index on kokoro_combination(pirate_hyado_spell_damage);
create index on kokoro_combination(pirate_io_spell_damage);
create index on kokoro_combination(pirate_jibaria_spell_damage);
create index on kokoro_combination(pirate_mera_spell_damage);

create index on kokoro_combination(pirate_bagi_slash_physical_spell_damage);
create index on kokoro_combination(pirate_dein_slash_physical_spell_damage);
create index on kokoro_combination(pirate_doruma_slash_physical_spell_damage);
create index on kokoro_combination(pirate_gira_slash_physical_spell_damage);
create index on kokoro_combination(pirate_hyado_slash_physical_spell_damage);
create index on kokoro_combination(pirate_io_slash_physical_spell_damage);
create index on kokoro_combination(pirate_jibaria_slash_physical_spell_damage);
create index on kokoro_combination(pirate_mera_slash_physical_spell_damage);

create index on kokoro_combination(pirate_bagi_hit_physical_spell_damage);
create index on kokoro_combination(pirate_dein_hit_physical_spell_damage);
create index on kokoro_combination(pirate_doruma_hit_physical_spell_damage);
create index on kokoro_combination(pirate_gira_hit_physical_spell_damage);
create index on kokoro_combination(pirate_hyado_hit_physical_spell_damage);
create index on kokoro_combination(pirate_io_hit_physical_spell_damage);
create index on kokoro_combination(pirate_jibaria_hit_physical_spell_damage);
create index on kokoro_combination(pirate_mera_hit_physical_spell_damage);

create index on kokoro_combination(pirate_bagi_breath_damage);
create index on kokoro_combination(pirate_dein_breath_damage);
create index on kokoro_combination(pirate_doruma_breath_damage);
create index on kokoro_combination(pirate_gira_breath_damage);
create index on kokoro_combination(pirate_hyado_breath_damage);
create index on kokoro_combination(pirate_io_breath_damage);
create index on kokoro_combination(pirate_jibaria_breath_damage);
create index on kokoro_combination(pirate_mera_breath_damage);

create index on kokoro_combination(pirate_speciality_healing);
create index on kokoro_combination(pirate_spell_healing);

create index on kokoro_combination(total_cost);
```