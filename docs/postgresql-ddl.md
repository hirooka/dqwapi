```
drop table if exists kokoro_combination_battle_master;
create table kokoro_combination_battle_master (
  k0id integer,
  k0grade integer,
  k1id integer,
  k1grade integer,
  k2id integer,
  k2grade integer,
  k3id integer,
  k3grade integer,

  max_battle_master_op_pattern text,
  max_battle_master_os_pattern text,
  max_battle_master_opos_pattern text,
  max_battle_master_opdx_pattern text,
  max_battle_master_ds_pattern text,
  
  /* -------- ATTRIBUTE -------- */
  
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
  
  battle_master_bagi_physical_spell_hit_damage integer,
  battle_master_dein_physical_spell_hit_damage integer,
  battle_master_doruma_physical_spell_hit_damage integer,
  battle_master_gira_physical_spell_hit_damage integer,
  battle_master_hyado_physical_spell_hit_damage integer,
  battle_master_io_physical_spell_hit_damage integer,
  battle_master_jibaria_physical_spell_hit_damage integer,
  battle_master_mera_physical_spell_hit_damage integer,

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
  
  /* -------- ANIMAL -------- */
  
  battle_master_bagi_slash_animal_damage integer,
  battle_master_dein_slash_animal_damage integer,
  battle_master_doruma_slash_animal_damage integer,
  battle_master_gira_slash_animal_damage integer,
  battle_master_hyado_slash_animal_damage integer,
  battle_master_io_slash_animal_damage integer,
  battle_master_jibaria_slash_animal_damage integer,
  battle_master_mera_slash_animal_damage integer,
  
  battle_master_bagi_hit_animal_damage integer,
  battle_master_dein_hit_animal_damage integer,
  battle_master_doruma_hit_animal_damage integer,
  battle_master_gira_hit_animal_damage integer,
  battle_master_hyado_hit_animal_damage integer,
  battle_master_io_hit_animal_damage integer,
  battle_master_jibaria_hit_animal_damage integer,
  battle_master_mera_hit_animal_damage integer,
  
  battle_master_bagi_spell_animal_damage integer,
  battle_master_dein_spell_animal_damage integer,
  battle_master_doruma_spell_animal_damage integer,
  battle_master_gira_spell_animal_damage integer,
  battle_master_hyado_spell_animal_damage integer,
  battle_master_io_spell_animal_damage integer,
  battle_master_jibaria_spell_animal_damage integer,
  battle_master_mera_spell_animal_damage integer,
  
  battle_master_bagi_physical_spell_slash_animal_damage integer,
  battle_master_dein_physical_spell_slash_animal_damage integer,
  battle_master_doruma_physical_spell_slash_animal_damage integer,
  battle_master_gira_physical_spell_slash_animal_damage integer,
  battle_master_hyado_physical_spell_slash_animal_damage integer,
  battle_master_io_physical_spell_slash_animal_damage integer,
  battle_master_jibaria_physical_spell_slash_animal_damage integer,
  battle_master_mera_physical_spell_slash_animal_damage integer,
  
  battle_master_bagi_physical_spell_hit_animal_damage integer,
  battle_master_dein_physical_spell_hit_animal_damage integer,
  battle_master_doruma_physical_spell_hit_animal_damage integer,
  battle_master_gira_physical_spell_hit_animal_damage integer,
  battle_master_hyado_physical_spell_hit_animal_damage integer,
  battle_master_io_physical_spell_hit_animal_damage integer,
  battle_master_jibaria_physical_spell_hit_animal_damage integer,
  battle_master_mera_physical_spell_hit_animal_damage integer,
  
  battle_master_bagi_breath_animal_damage integer,
  battle_master_dein_breath_animal_damage integer,
  battle_master_doruma_breath_animal_damage integer,
  battle_master_gira_breath_animal_damage integer,
  battle_master_hyado_breath_animal_damage integer,
  battle_master_io_breath_animal_damage integer,
  battle_master_jibaria_breath_animal_damage integer,
  battle_master_mera_breath_animal_damage integer,
  
  /* -------- BIRD -------- */
  
  battle_master_bagi_slash_bird_damage integer,
  battle_master_dein_slash_bird_damage integer,
  battle_master_doruma_slash_bird_damage integer,
  battle_master_gira_slash_bird_damage integer,
  battle_master_hyado_slash_bird_damage integer,
  battle_master_io_slash_bird_damage integer,
  battle_master_jibaria_slash_bird_damage integer,
  battle_master_mera_slash_bird_damage integer,
  
  battle_master_bagi_hit_bird_damage integer,
  battle_master_dein_hit_bird_damage integer,
  battle_master_doruma_hit_bird_damage integer,
  battle_master_gira_hit_bird_damage integer,
  battle_master_hyado_hit_bird_damage integer,
  battle_master_io_hit_bird_damage integer,
  battle_master_jibaria_hit_bird_damage integer,
  battle_master_mera_hit_bird_damage integer,
  
  battle_master_bagi_spell_bird_damage integer,
  battle_master_dein_spell_bird_damage integer,
  battle_master_doruma_spell_bird_damage integer,
  battle_master_gira_spell_bird_damage integer,
  battle_master_hyado_spell_bird_damage integer,
  battle_master_io_spell_bird_damage integer,
  battle_master_jibaria_spell_bird_damage integer,
  battle_master_mera_spell_bird_damage integer,
  
  battle_master_bagi_physical_spell_slash_bird_damage integer,
  battle_master_dein_physical_spell_slash_bird_damage integer,
  battle_master_doruma_physical_spell_slash_bird_damage integer,
  battle_master_gira_physical_spell_slash_bird_damage integer,
  battle_master_hyado_physical_spell_slash_bird_damage integer,
  battle_master_io_physical_spell_slash_bird_damage integer,
  battle_master_jibaria_physical_spell_slash_bird_damage integer,
  battle_master_mera_physical_spell_slash_bird_damage integer,
  
  battle_master_bagi_physical_spell_hit_bird_damage integer,
  battle_master_dein_physical_spell_hit_bird_damage integer,
  battle_master_doruma_physical_spell_hit_bird_damage integer,
  battle_master_gira_physical_spell_hit_bird_damage integer,
  battle_master_hyado_physical_spell_hit_bird_damage integer,
  battle_master_io_physical_spell_hit_bird_damage integer,
  battle_master_jibaria_physical_spell_hit_bird_damage integer,
  battle_master_mera_physical_spell_hit_bird_damage integer,
  
  battle_master_bagi_breath_bird_damage integer,
  battle_master_dein_breath_bird_damage integer,
  battle_master_doruma_breath_bird_damage integer,
  battle_master_gira_breath_bird_damage integer,
  battle_master_hyado_breath_bird_damage integer,
  battle_master_io_breath_bird_damage integer,
  battle_master_jibaria_breath_bird_damage integer,
  battle_master_mera_breath_bird_damage integer,
  
  /* -------- DEVIL -------- */
  
  battle_master_bagi_slash_devil_damage integer,
  battle_master_dein_slash_devil_damage integer,
  battle_master_doruma_slash_devil_damage integer,
  battle_master_gira_slash_devil_damage integer,
  battle_master_hyado_slash_devil_damage integer,
  battle_master_io_slash_devil_damage integer,
  battle_master_jibaria_slash_devil_damage integer,
  battle_master_mera_slash_devil_damage integer,
  
  battle_master_bagi_hit_devil_damage integer,
  battle_master_dein_hit_devil_damage integer,
  battle_master_doruma_hit_devil_damage integer,
  battle_master_gira_hit_devil_damage integer,
  battle_master_hyado_hit_devil_damage integer,
  battle_master_io_hit_devil_damage integer,
  battle_master_jibaria_hit_devil_damage integer,
  battle_master_mera_hit_devil_damage integer,
  
  battle_master_bagi_spell_devil_damage integer,
  battle_master_dein_spell_devil_damage integer,
  battle_master_doruma_spell_devil_damage integer,
  battle_master_gira_spell_devil_damage integer,
  battle_master_hyado_spell_devil_damage integer,
  battle_master_io_spell_devil_damage integer,
  battle_master_jibaria_spell_devil_damage integer,
  battle_master_mera_spell_devil_damage integer,
  
  battle_master_bagi_physical_spell_slash_devil_damage integer,
  battle_master_dein_physical_spell_slash_devil_damage integer,
  battle_master_doruma_physical_spell_slash_devil_damage integer,
  battle_master_gira_physical_spell_slash_devil_damage integer,
  battle_master_hyado_physical_spell_slash_devil_damage integer,
  battle_master_io_physical_spell_slash_devil_damage integer,
  battle_master_jibaria_physical_spell_slash_devil_damage integer,
  battle_master_mera_physical_spell_slash_devil_damage integer,
  
  battle_master_bagi_physical_spell_hit_devil_damage integer,
  battle_master_dein_physical_spell_hit_devil_damage integer,
  battle_master_doruma_physical_spell_hit_devil_damage integer,
  battle_master_gira_physical_spell_hit_devil_damage integer,
  battle_master_hyado_physical_spell_hit_devil_damage integer,
  battle_master_io_physical_spell_hit_devil_damage integer,
  battle_master_jibaria_physical_spell_hit_devil_damage integer,
  battle_master_mera_physical_spell_hit_devil_damage integer,
  
  battle_master_bagi_breath_devil_damage integer,
  battle_master_dein_breath_devil_damage integer,
  battle_master_doruma_breath_devil_damage integer,
  battle_master_gira_breath_devil_damage integer,
  battle_master_hyado_breath_devil_damage integer,
  battle_master_io_breath_devil_damage integer,
  battle_master_jibaria_breath_devil_damage integer,
  battle_master_mera_breath_devil_damage integer,
  
  /* -------- DRAGON -------- */
  
  battle_master_bagi_slash_dragon_damage integer,
  battle_master_dein_slash_dragon_damage integer,
  battle_master_doruma_slash_dragon_damage integer,
  battle_master_gira_slash_dragon_damage integer,
  battle_master_hyado_slash_dragon_damage integer,
  battle_master_io_slash_dragon_damage integer,
  battle_master_jibaria_slash_dragon_damage integer,
  battle_master_mera_slash_dragon_damage integer,
  
  battle_master_bagi_hit_dragon_damage integer,
  battle_master_dein_hit_dragon_damage integer,
  battle_master_doruma_hit_dragon_damage integer,
  battle_master_gira_hit_dragon_damage integer,
  battle_master_hyado_hit_dragon_damage integer,
  battle_master_io_hit_dragon_damage integer,
  battle_master_jibaria_hit_dragon_damage integer,
  battle_master_mera_hit_dragon_damage integer,
  
  battle_master_bagi_spell_dragon_damage integer,
  battle_master_dein_spell_dragon_damage integer,
  battle_master_doruma_spell_dragon_damage integer,
  battle_master_gira_spell_dragon_damage integer,
  battle_master_hyado_spell_dragon_damage integer,
  battle_master_io_spell_dragon_damage integer,
  battle_master_jibaria_spell_dragon_damage integer,
  battle_master_mera_spell_dragon_damage integer,
  
  battle_master_bagi_physical_spell_slash_dragon_damage integer,
  battle_master_dein_physical_spell_slash_dragon_damage integer,
  battle_master_doruma_physical_spell_slash_dragon_damage integer,
  battle_master_gira_physical_spell_slash_dragon_damage integer,
  battle_master_hyado_physical_spell_slash_dragon_damage integer,
  battle_master_io_physical_spell_slash_dragon_damage integer,
  battle_master_jibaria_physical_spell_slash_dragon_damage integer,
  battle_master_mera_physical_spell_slash_dragon_damage integer,
  
  battle_master_bagi_physical_spell_hit_dragon_damage integer,
  battle_master_dein_physical_spell_hit_dragon_damage integer,
  battle_master_doruma_physical_spell_hit_dragon_damage integer,
  battle_master_gira_physical_spell_hit_dragon_damage integer,
  battle_master_hyado_physical_spell_hit_dragon_damage integer,
  battle_master_io_physical_spell_hit_dragon_damage integer,
  battle_master_jibaria_physical_spell_hit_dragon_damage integer,
  battle_master_mera_physical_spell_hit_dragon_damage integer,
  
  battle_master_bagi_breath_dragon_damage integer,
  battle_master_dein_breath_dragon_damage integer,
  battle_master_doruma_breath_dragon_damage integer,
  battle_master_gira_breath_dragon_damage integer,
  battle_master_hyado_breath_dragon_damage integer,
  battle_master_io_breath_dragon_damage integer,
  battle_master_jibaria_breath_dragon_damage integer,
  battle_master_mera_breath_dragon_damage integer,
  
  /* -------- ELEMENT -------- */
  
  battle_master_bagi_slash_element_damage integer,
  battle_master_dein_slash_element_damage integer,
  battle_master_doruma_slash_element_damage integer,
  battle_master_gira_slash_element_damage integer,
  battle_master_hyado_slash_element_damage integer,
  battle_master_io_slash_element_damage integer,
  battle_master_jibaria_slash_element_damage integer,
  battle_master_mera_slash_element_damage integer,
  
  battle_master_bagi_hit_element_damage integer,
  battle_master_dein_hit_element_damage integer,
  battle_master_doruma_hit_element_damage integer,
  battle_master_gira_hit_element_damage integer,
  battle_master_hyado_hit_element_damage integer,
  battle_master_io_hit_element_damage integer,
  battle_master_jibaria_hit_element_damage integer,
  battle_master_mera_hit_element_damage integer,
  
  battle_master_bagi_spell_element_damage integer,
  battle_master_dein_spell_element_damage integer,
  battle_master_doruma_spell_element_damage integer,
  battle_master_gira_spell_element_damage integer,
  battle_master_hyado_spell_element_damage integer,
  battle_master_io_spell_element_damage integer,
  battle_master_jibaria_spell_element_damage integer,
  battle_master_mera_spell_element_damage integer,
  
  battle_master_bagi_physical_spell_slash_element_damage integer,
  battle_master_dein_physical_spell_slash_element_damage integer,
  battle_master_doruma_physical_spell_slash_element_damage integer,
  battle_master_gira_physical_spell_slash_element_damage integer,
  battle_master_hyado_physical_spell_slash_element_damage integer,
  battle_master_io_physical_spell_slash_element_damage integer,
  battle_master_jibaria_physical_spell_slash_element_damage integer,
  battle_master_mera_physical_spell_slash_element_damage integer,
  
  battle_master_bagi_physical_spell_hit_element_damage integer,
  battle_master_dein_physical_spell_hit_element_damage integer,
  battle_master_doruma_physical_spell_hit_element_damage integer,
  battle_master_gira_physical_spell_hit_element_damage integer,
  battle_master_hyado_physical_spell_hit_element_damage integer,
  battle_master_io_physical_spell_hit_element_damage integer,
  battle_master_jibaria_physical_spell_hit_element_damage integer,
  battle_master_mera_physical_spell_hit_element_damage integer,
  
  battle_master_bagi_breath_element_damage integer,
  battle_master_dein_breath_element_damage integer,
  battle_master_doruma_breath_element_damage integer,
  battle_master_gira_breath_element_damage integer,
  battle_master_hyado_breath_element_damage integer,
  battle_master_io_breath_element_damage integer,
  battle_master_jibaria_breath_element_damage integer,
  battle_master_mera_breath_element_damage integer,
  
  /* -------- INSECT -------- */
  
  battle_master_bagi_slash_insect_damage integer,
  battle_master_dein_slash_insect_damage integer,
  battle_master_doruma_slash_insect_damage integer,
  battle_master_gira_slash_insect_damage integer,
  battle_master_hyado_slash_insect_damage integer,
  battle_master_io_slash_insect_damage integer,
  battle_master_jibaria_slash_insect_damage integer,
  battle_master_mera_slash_insect_damage integer,
  
  battle_master_bagi_hit_insect_damage integer,
  battle_master_dein_hit_insect_damage integer,
  battle_master_doruma_hit_insect_damage integer,
  battle_master_gira_hit_insect_damage integer,
  battle_master_hyado_hit_insect_damage integer,
  battle_master_io_hit_insect_damage integer,
  battle_master_jibaria_hit_insect_damage integer,
  battle_master_mera_hit_insect_damage integer,
  
  battle_master_bagi_spell_insect_damage integer,
  battle_master_dein_spell_insect_damage integer,
  battle_master_doruma_spell_insect_damage integer,
  battle_master_gira_spell_insect_damage integer,
  battle_master_hyado_spell_insect_damage integer,
  battle_master_io_spell_insect_damage integer,
  battle_master_jibaria_spell_insect_damage integer,
  battle_master_mera_spell_insect_damage integer,
  
  battle_master_bagi_physical_spell_slash_insect_damage integer,
  battle_master_dein_physical_spell_slash_insect_damage integer,
  battle_master_doruma_physical_spell_slash_insect_damage integer,
  battle_master_gira_physical_spell_slash_insect_damage integer,
  battle_master_hyado_physical_spell_slash_insect_damage integer,
  battle_master_io_physical_spell_slash_insect_damage integer,
  battle_master_jibaria_physical_spell_slash_insect_damage integer,
  battle_master_mera_physical_spell_slash_insect_damage integer,
  
  battle_master_bagi_physical_spell_hit_insect_damage integer,
  battle_master_dein_physical_spell_hit_insect_damage integer,
  battle_master_doruma_physical_spell_hit_insect_damage integer,
  battle_master_gira_physical_spell_hit_insect_damage integer,
  battle_master_hyado_physical_spell_hit_insect_damage integer,
  battle_master_io_physical_spell_hit_insect_damage integer,
  battle_master_jibaria_physical_spell_hit_insect_damage integer,
  battle_master_mera_physical_spell_hit_insect_damage integer,
  
  battle_master_bagi_breath_insect_damage integer,
  battle_master_dein_breath_insect_damage integer,
  battle_master_doruma_breath_insect_damage integer,
  battle_master_gira_breath_insect_damage integer,
  battle_master_hyado_breath_insect_damage integer,
  battle_master_io_breath_insect_damage integer,
  battle_master_jibaria_breath_insect_damage integer,
  battle_master_mera_breath_insect_damage integer,
  
  /* -------- MACHINE -------- */
  
  battle_master_bagi_slash_machine_damage integer,
  battle_master_dein_slash_machine_damage integer,
  battle_master_doruma_slash_machine_damage integer,
  battle_master_gira_slash_machine_damage integer,
  battle_master_hyado_slash_machine_damage integer,
  battle_master_io_slash_machine_damage integer,
  battle_master_jibaria_slash_machine_damage integer,
  battle_master_mera_slash_machine_damage integer,
  
  battle_master_bagi_hit_machine_damage integer,
  battle_master_dein_hit_machine_damage integer,
  battle_master_doruma_hit_machine_damage integer,
  battle_master_gira_hit_machine_damage integer,
  battle_master_hyado_hit_machine_damage integer,
  battle_master_io_hit_machine_damage integer,
  battle_master_jibaria_hit_machine_damage integer,
  battle_master_mera_hit_machine_damage integer,
  
  battle_master_bagi_spell_machine_damage integer,
  battle_master_dein_spell_machine_damage integer,
  battle_master_doruma_spell_machine_damage integer,
  battle_master_gira_spell_machine_damage integer,
  battle_master_hyado_spell_machine_damage integer,
  battle_master_io_spell_machine_damage integer,
  battle_master_jibaria_spell_machine_damage integer,
  battle_master_mera_spell_machine_damage integer,
  
  battle_master_bagi_physical_spell_slash_machine_damage integer,
  battle_master_dein_physical_spell_slash_machine_damage integer,
  battle_master_doruma_physical_spell_slash_machine_damage integer,
  battle_master_gira_physical_spell_slash_machine_damage integer,
  battle_master_hyado_physical_spell_slash_machine_damage integer,
  battle_master_io_physical_spell_slash_machine_damage integer,
  battle_master_jibaria_physical_spell_slash_machine_damage integer,
  battle_master_mera_physical_spell_slash_machine_damage integer,
  
  battle_master_bagi_physical_spell_hit_machine_damage integer,
  battle_master_dein_physical_spell_hit_machine_damage integer,
  battle_master_doruma_physical_spell_hit_machine_damage integer,
  battle_master_gira_physical_spell_hit_machine_damage integer,
  battle_master_hyado_physical_spell_hit_machine_damage integer,
  battle_master_io_physical_spell_hit_machine_damage integer,
  battle_master_jibaria_physical_spell_hit_machine_damage integer,
  battle_master_mera_physical_spell_hit_machine_damage integer,
  
  battle_master_bagi_breath_machine_damage integer,
  battle_master_dein_breath_machine_damage integer,
  battle_master_doruma_breath_machine_damage integer,
  battle_master_gira_breath_machine_damage integer,
  battle_master_hyado_breath_machine_damage integer,
  battle_master_io_breath_machine_damage integer,
  battle_master_jibaria_breath_machine_damage integer,
  battle_master_mera_breath_machine_damage integer,
  
  /* -------- MATERIAL -------- */
  
  battle_master_bagi_slash_material_damage integer,
  battle_master_dein_slash_material_damage integer,
  battle_master_doruma_slash_material_damage integer,
  battle_master_gira_slash_material_damage integer,
  battle_master_hyado_slash_material_damage integer,
  battle_master_io_slash_material_damage integer,
  battle_master_jibaria_slash_material_damage integer,
  battle_master_mera_slash_material_damage integer,
  
  battle_master_bagi_hit_material_damage integer,
  battle_master_dein_hit_material_damage integer,
  battle_master_doruma_hit_material_damage integer,
  battle_master_gira_hit_material_damage integer,
  battle_master_hyado_hit_material_damage integer,
  battle_master_io_hit_material_damage integer,
  battle_master_jibaria_hit_material_damage integer,
  battle_master_mera_hit_material_damage integer,
  
  battle_master_bagi_spell_material_damage integer,
  battle_master_dein_spell_material_damage integer,
  battle_master_doruma_spell_material_damage integer,
  battle_master_gira_spell_material_damage integer,
  battle_master_hyado_spell_material_damage integer,
  battle_master_io_spell_material_damage integer,
  battle_master_jibaria_spell_material_damage integer,
  battle_master_mera_spell_material_damage integer,
  
  battle_master_bagi_physical_spell_slash_material_damage integer,
  battle_master_dein_physical_spell_slash_material_damage integer,
  battle_master_doruma_physical_spell_slash_material_damage integer,
  battle_master_gira_physical_spell_slash_material_damage integer,
  battle_master_hyado_physical_spell_slash_material_damage integer,
  battle_master_io_physical_spell_slash_material_damage integer,
  battle_master_jibaria_physical_spell_slash_material_damage integer,
  battle_master_mera_physical_spell_slash_material_damage integer,
  
  battle_master_bagi_physical_spell_hit_material_damage integer,
  battle_master_dein_physical_spell_hit_material_damage integer,
  battle_master_doruma_physical_spell_hit_material_damage integer,
  battle_master_gira_physical_spell_hit_material_damage integer,
  battle_master_hyado_physical_spell_hit_material_damage integer,
  battle_master_io_physical_spell_hit_material_damage integer,
  battle_master_jibaria_physical_spell_hit_material_damage integer,
  battle_master_mera_physical_spell_hit_material_damage integer,
  
  battle_master_bagi_breath_material_damage integer,
  battle_master_dein_breath_material_damage integer,
  battle_master_doruma_breath_material_damage integer,
  battle_master_gira_breath_material_damage integer,
  battle_master_hyado_breath_material_damage integer,
  battle_master_io_breath_material_damage integer,
  battle_master_jibaria_breath_material_damage integer,
  battle_master_mera_breath_material_damage integer,
  
  /* -------- PHANTOM -------- */
  
  battle_master_bagi_slash_phantom_damage integer,
  battle_master_dein_slash_phantom_damage integer,
  battle_master_doruma_slash_phantom_damage integer,
  battle_master_gira_slash_phantom_damage integer,
  battle_master_hyado_slash_phantom_damage integer,
  battle_master_io_slash_phantom_damage integer,
  battle_master_jibaria_slash_phantom_damage integer,
  battle_master_mera_slash_phantom_damage integer,
  
  battle_master_bagi_hit_phantom_damage integer,
  battle_master_dein_hit_phantom_damage integer,
  battle_master_doruma_hit_phantom_damage integer,
  battle_master_gira_hit_phantom_damage integer,
  battle_master_hyado_hit_phantom_damage integer,
  battle_master_io_hit_phantom_damage integer,
  battle_master_jibaria_hit_phantom_damage integer,
  battle_master_mera_hit_phantom_damage integer,
  
  battle_master_bagi_spell_phantom_damage integer,
  battle_master_dein_spell_phantom_damage integer,
  battle_master_doruma_spell_phantom_damage integer,
  battle_master_gira_spell_phantom_damage integer,
  battle_master_hyado_spell_phantom_damage integer,
  battle_master_io_spell_phantom_damage integer,
  battle_master_jibaria_spell_phantom_damage integer,
  battle_master_mera_spell_phantom_damage integer,
  
  battle_master_bagi_physical_spell_slash_phantom_damage integer,
  battle_master_dein_physical_spell_slash_phantom_damage integer,
  battle_master_doruma_physical_spell_slash_phantom_damage integer,
  battle_master_gira_physical_spell_slash_phantom_damage integer,
  battle_master_hyado_physical_spell_slash_phantom_damage integer,
  battle_master_io_physical_spell_slash_phantom_damage integer,
  battle_master_jibaria_physical_spell_slash_phantom_damage integer,
  battle_master_mera_physical_spell_slash_phantom_damage integer,
  
  battle_master_bagi_physical_spell_hit_phantom_damage integer,
  battle_master_dein_physical_spell_hit_phantom_damage integer,
  battle_master_doruma_physical_spell_hit_phantom_damage integer,
  battle_master_gira_physical_spell_hit_phantom_damage integer,
  battle_master_hyado_physical_spell_hit_phantom_damage integer,
  battle_master_io_physical_spell_hit_phantom_damage integer,
  battle_master_jibaria_physical_spell_hit_phantom_damage integer,
  battle_master_mera_physical_spell_hit_phantom_damage integer,
  
  battle_master_bagi_breath_phantom_damage integer,
  battle_master_dein_breath_phantom_damage integer,
  battle_master_doruma_breath_phantom_damage integer,
  battle_master_gira_breath_phantom_damage integer,
  battle_master_hyado_breath_phantom_damage integer,
  battle_master_io_breath_phantom_damage integer,
  battle_master_jibaria_breath_phantom_damage integer,
  battle_master_mera_breath_phantom_damage integer,
  
  
  /* -------- PLANT -------- */
  
  battle_master_bagi_slash_plant_damage integer,
  battle_master_dein_slash_plant_damage integer,
  battle_master_doruma_slash_plant_damage integer,
  battle_master_gira_slash_plant_damage integer,
  battle_master_hyado_slash_plant_damage integer,
  battle_master_io_slash_plant_damage integer,
  battle_master_jibaria_slash_plant_damage integer,
  battle_master_mera_slash_plant_damage integer,
  
  battle_master_bagi_hit_plant_damage integer,
  battle_master_dein_hit_plant_damage integer,
  battle_master_doruma_hit_plant_damage integer,
  battle_master_gira_hit_plant_damage integer,
  battle_master_hyado_hit_plant_damage integer,
  battle_master_io_hit_plant_damage integer,
  battle_master_jibaria_hit_plant_damage integer,
  battle_master_mera_hit_plant_damage integer,
  
  battle_master_bagi_spell_plant_damage integer,
  battle_master_dein_spell_plant_damage integer,
  battle_master_doruma_spell_plant_damage integer,
  battle_master_gira_spell_plant_damage integer,
  battle_master_hyado_spell_plant_damage integer,
  battle_master_io_spell_plant_damage integer,
  battle_master_jibaria_spell_plant_damage integer,
  battle_master_mera_spell_plant_damage integer,
  
  battle_master_bagi_physical_spell_slash_plant_damage integer,
  battle_master_dein_physical_spell_slash_plant_damage integer,
  battle_master_doruma_physical_spell_slash_plant_damage integer,
  battle_master_gira_physical_spell_slash_plant_damage integer,
  battle_master_hyado_physical_spell_slash_plant_damage integer,
  battle_master_io_physical_spell_slash_plant_damage integer,
  battle_master_jibaria_physical_spell_slash_plant_damage integer,
  battle_master_mera_physical_spell_slash_plant_damage integer,
  
  battle_master_bagi_physical_spell_hit_plant_damage integer,
  battle_master_dein_physical_spell_hit_plant_damage integer,
  battle_master_doruma_physical_spell_hit_plant_damage integer,
  battle_master_gira_physical_spell_hit_plant_damage integer,
  battle_master_hyado_physical_spell_hit_plant_damage integer,
  battle_master_io_physical_spell_hit_plant_damage integer,
  battle_master_jibaria_physical_spell_hit_plant_damage integer,
  battle_master_mera_physical_spell_hit_plant_damage integer,
  
  battle_master_bagi_breath_plant_damage integer,
  battle_master_dein_breath_plant_damage integer,
  battle_master_doruma_breath_plant_damage integer,
  battle_master_gira_breath_plant_damage integer,
  battle_master_hyado_breath_plant_damage integer,
  battle_master_io_breath_plant_damage integer,
  battle_master_jibaria_breath_plant_damage integer,
  battle_master_mera_breath_plant_damage integer,
  
  /* -------- SLIME -------- */
  
  battle_master_bagi_slash_slime_damage integer,
  battle_master_dein_slash_slime_damage integer,
  battle_master_doruma_slash_slime_damage integer,
  battle_master_gira_slash_slime_damage integer,
  battle_master_hyado_slash_slime_damage integer,
  battle_master_io_slash_slime_damage integer,
  battle_master_jibaria_slash_slime_damage integer,
  battle_master_mera_slash_slime_damage integer,
  
  battle_master_bagi_hit_slime_damage integer,
  battle_master_dein_hit_slime_damage integer,
  battle_master_doruma_hit_slime_damage integer,
  battle_master_gira_hit_slime_damage integer,
  battle_master_hyado_hit_slime_damage integer,
  battle_master_io_hit_slime_damage integer,
  battle_master_jibaria_hit_slime_damage integer,
  battle_master_mera_hit_slime_damage integer,
  
  battle_master_bagi_spell_slime_damage integer,
  battle_master_dein_spell_slime_damage integer,
  battle_master_doruma_spell_slime_damage integer,
  battle_master_gira_spell_slime_damage integer,
  battle_master_hyado_spell_slime_damage integer,
  battle_master_io_spell_slime_damage integer,
  battle_master_jibaria_spell_slime_damage integer,
  battle_master_mera_spell_slime_damage integer,
  
  battle_master_bagi_physical_spell_slash_slime_damage integer,
  battle_master_dein_physical_spell_slash_slime_damage integer,
  battle_master_doruma_physical_spell_slash_slime_damage integer,
  battle_master_gira_physical_spell_slash_slime_damage integer,
  battle_master_hyado_physical_spell_slash_slime_damage integer,
  battle_master_io_physical_spell_slash_slime_damage integer,
  battle_master_jibaria_physical_spell_slash_slime_damage integer,
  battle_master_mera_physical_spell_slash_slime_damage integer,
  
  battle_master_bagi_physical_spell_hit_slime_damage integer,
  battle_master_dein_physical_spell_hit_slime_damage integer,
  battle_master_doruma_physical_spell_hit_slime_damage integer,
  battle_master_gira_physical_spell_hit_slime_damage integer,
  battle_master_hyado_physical_spell_hit_slime_damage integer,
  battle_master_io_physical_spell_hit_slime_damage integer,
  battle_master_jibaria_physical_spell_hit_slime_damage integer,
  battle_master_mera_physical_spell_hit_slime_damage integer,
  
  battle_master_bagi_breath_slime_damage integer,
  battle_master_dein_breath_slime_damage integer,
  battle_master_doruma_breath_slime_damage integer,
  battle_master_gira_breath_slime_damage integer,
  battle_master_hyado_breath_slime_damage integer,
  battle_master_io_breath_slime_damage integer,
  battle_master_jibaria_breath_slime_damage integer,
  battle_master_mera_breath_slime_damage integer,
  
  /* -------- WATER -------- */
  
  battle_master_bagi_slash_water_damage integer,
  battle_master_dein_slash_water_damage integer,
  battle_master_doruma_slash_water_damage integer,
  battle_master_gira_slash_water_damage integer,
  battle_master_hyado_slash_water_damage integer,
  battle_master_io_slash_water_damage integer,
  battle_master_jibaria_slash_water_damage integer,
  battle_master_mera_slash_water_damage integer,
  
  battle_master_bagi_hit_water_damage integer,
  battle_master_dein_hit_water_damage integer,
  battle_master_doruma_hit_water_damage integer,
  battle_master_gira_hit_water_damage integer,
  battle_master_hyado_hit_water_damage integer,
  battle_master_io_hit_water_damage integer,
  battle_master_jibaria_hit_water_damage integer,
  battle_master_mera_hit_water_damage integer,
  
  battle_master_bagi_spell_water_damage integer,
  battle_master_dein_spell_water_damage integer,
  battle_master_doruma_spell_water_damage integer,
  battle_master_gira_spell_water_damage integer,
  battle_master_hyado_spell_water_damage integer,
  battle_master_io_spell_water_damage integer,
  battle_master_jibaria_spell_water_damage integer,
  battle_master_mera_spell_water_damage integer,
  
  battle_master_bagi_physical_spell_slash_water_damage integer,
  battle_master_dein_physical_spell_slash_water_damage integer,
  battle_master_doruma_physical_spell_slash_water_damage integer,
  battle_master_gira_physical_spell_slash_water_damage integer,
  battle_master_hyado_physical_spell_slash_water_damage integer,
  battle_master_io_physical_spell_slash_water_damage integer,
  battle_master_jibaria_physical_spell_slash_water_damage integer,
  battle_master_mera_physical_spell_slash_water_damage integer,
  
  battle_master_bagi_physical_spell_hit_water_damage integer,
  battle_master_dein_physical_spell_hit_water_damage integer,
  battle_master_doruma_physical_spell_hit_water_damage integer,
  battle_master_gira_physical_spell_hit_water_damage integer,
  battle_master_hyado_physical_spell_hit_water_damage integer,
  battle_master_io_physical_spell_hit_water_damage integer,
  battle_master_jibaria_physical_spell_hit_water_damage integer,
  battle_master_mera_physical_spell_hit_water_damage integer,
  
  battle_master_bagi_breath_water_damage integer,
  battle_master_dein_breath_water_damage integer,
  battle_master_doruma_breath_water_damage integer,
  battle_master_gira_breath_water_damage integer,
  battle_master_hyado_breath_water_damage integer,
  battle_master_io_breath_water_damage integer,
  battle_master_jibaria_breath_water_damage integer,
  battle_master_mera_breath_water_damage integer,
  
  /* -------- ZOMBIE -------- */
  
  battle_master_bagi_slash_zombie_damage integer,
  battle_master_dein_slash_zombie_damage integer,
  battle_master_doruma_slash_zombie_damage integer,
  battle_master_gira_slash_zombie_damage integer,
  battle_master_hyado_slash_zombie_damage integer,
  battle_master_io_slash_zombie_damage integer,
  battle_master_jibaria_slash_zombie_damage integer,
  battle_master_mera_slash_zombie_damage integer,
  
  battle_master_bagi_hit_zombie_damage integer,
  battle_master_dein_hit_zombie_damage integer,
  battle_master_doruma_hit_zombie_damage integer,
  battle_master_gira_hit_zombie_damage integer,
  battle_master_hyado_hit_zombie_damage integer,
  battle_master_io_hit_zombie_damage integer,
  battle_master_jibaria_hit_zombie_damage integer,
  battle_master_mera_hit_zombie_damage integer,  
  
  battle_master_bagi_spell_zombie_damage integer,
  battle_master_dein_spell_zombie_damage integer,
  battle_master_doruma_spell_zombie_damage integer,
  battle_master_gira_spell_zombie_damage integer,
  battle_master_hyado_spell_zombie_damage integer,
  battle_master_io_spell_zombie_damage integer,
  battle_master_jibaria_spell_zombie_damage integer,
  battle_master_mera_spell_zombie_damage integer,
  
  battle_master_bagi_physical_spell_slash_zombie_damage integer,
  battle_master_dein_physical_spell_slash_zombie_damage integer,
  battle_master_doruma_physical_spell_slash_zombie_damage integer,
  battle_master_gira_physical_spell_slash_zombie_damage integer,
  battle_master_hyado_physical_spell_slash_zombie_damage integer,
  battle_master_io_physical_spell_slash_zombie_damage integer,
  battle_master_jibaria_physical_spell_slash_zombie_damage integer,
  battle_master_mera_physical_spell_slash_zombie_damage integer,
  
  battle_master_bagi_physical_spell_hit_zombie_damage integer,
  battle_master_dein_physical_spell_hit_zombie_damage integer,
  battle_master_doruma_physical_spell_hit_zombie_damage integer,
  battle_master_gira_physical_spell_hit_zombie_damage integer,
  battle_master_hyado_physical_spell_hit_zombie_damage integer,
  battle_master_io_physical_spell_hit_zombie_damage integer,
  battle_master_jibaria_physical_spell_hit_zombie_damage integer,
  battle_master_mera_physical_spell_hit_zombie_damage integer,
  
  battle_master_bagi_breath_zombie_damage integer,
  battle_master_dein_breath_zombie_damage integer,
  battle_master_doruma_breath_zombie_damage integer,
  battle_master_gira_breath_zombie_damage integer,
  battle_master_hyado_breath_zombie_damage integer,
  battle_master_io_breath_zombie_damage integer,
  battle_master_jibaria_breath_zombie_damage integer,
  battle_master_mera_breath_zombie_damage integer,
  
  total_cost integer
);
```

```
create index on kokoro_combination_battle_master(battle_master_bagi_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_bagi_slash_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_damage);

create index on kokoro_combination_battle_master(battle_master_bagi_breath_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_damage);
  
create index on kokoro_combination_battle_master(battle_master_speciality_healing);
create index on kokoro_combination_battle_master(battle_master_spell_healing);
  
  /* -------- ANIMAL -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_animal_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_animal_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_animal_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_animal_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_animal_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_animal_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_animal_damage);
  
  /* -------- BIRD -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_bird_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_bird_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_bird_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_bird_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_bird_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_bird_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_bird_damage);
  
  /* -------- DEVIL -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_devil_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_devil_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_devil_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_devil_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_devil_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_devil_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_devil_damage);
  
  /* -------- DRAGON -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_dragon_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_dragon_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_dragon_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_dragon_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_dragon_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_dragon_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_dragon_damage);
  
  /* -------- ELEMENT -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_element_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_element_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_element_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_element_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_element_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_element_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_element_damage);
  
  /* -------- INSECT -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_insect_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_insect_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_insect_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_insect_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_insect_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_insect_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_insect_damage);
  
  /* -------- MACHINE -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_machine_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_machine_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_machine_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_machine_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_machine_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_machine_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_machine_damage);
  
  /* -------- MATERIAL -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_material_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_material_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_material_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_material_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_material_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_material_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_material_damage);
  
  /* -------- PHANTOM -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_phantom_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_phantom_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_phantom_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_phantom_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_phantom_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_phantom_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_phantom_damage);
  
  
  /* -------- PLANT -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_plant_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_plant_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_plant_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_plant_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_plant_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_plant_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_plant_damage);
  
  /* -------- SLIME -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_slime_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_slime_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_slime_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_slime_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_slime_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_slime_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_slime_damage);
  
  /* -------- WATER -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_water_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_water_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_water_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_water_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_water_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_water_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_water_damage);
  
  /* -------- ZOMBIE -------- */
  
create index on kokoro_combination_battle_master(battle_master_bagi_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_slash_zombie_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_hit_zombie_damage);  
  
create index on kokoro_combination_battle_master(battle_master_bagi_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_spell_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_spell_zombie_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_slash_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_slash_zombie_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_physical_spell_hit_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_physical_spell_hit_zombie_damage);
  
create index on kokoro_combination_battle_master(battle_master_bagi_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_dein_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_doruma_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_gira_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_hyado_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_io_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_jibaria_breath_zombie_damage);
create index on kokoro_combination_battle_master(battle_master_mera_breath_zombie_damage);
  
create index on kokoro_combination_battle_master(total_cost);
```