```
/* job_type = 0:battle_master, 1:ranger, 2:sage, 3:paladin, 4:armamentalist */
/* type = 0:r, 1:y, 2:b, 3:p, 4:g */
create or replace function get_parameter_value(job_type integer, k0t integer, k0v integer, k1t integer, k1v integer, k2t integer, k2v integer, k3t integer, k3v integer)
  returns integer as $$
begin
  if job_type = 0 then /* battle_master */
    if k0t = 0 then
      if k1t = 0 then
        if k2t = 0 or k2t = 1 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 1 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 0 then
        if k2t = 0 or k2t = 1 then
          return ceil(k0v*1.2)+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 1 then
          return ceil(k0v*1.2)+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    elseif k0t != 0 then
      if k1t = 0 then
        if k2t = 0 or k2t = 1 then
          return k0v+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 1 then
          return k0v+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 0 then
        if k2t = 0 or k2t = 1 then
          return k0v+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 1 then
          return k0v+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    end if;
  elseif job_type = 1 then /* ranger */
    if k0t = 2 then
      if k1t = 2 then
        if k2t = 0 or k2t = 2 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 2 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 2 then
        if k2t = 0 or k2t = 2 then
          return ceil(k0v*1.2)+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 2 then
          return ceil(k0v*1.2)+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    elseif k0t != 2 then
      if k1t = 2 then
        if k2t = 0 or k2t = 2 then
          return k0v+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 2 then
          return k0v+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 2 then
        if k2t = 0 or k2t = 2 then
          return k0v+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 0 and k2t != 2 then
          return k0v+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    end if;
  elseif job_type = 2 then /* sage */
    if k0t = 3 or k0t = 4 then
      if k1t = 3 or k1t = 4 then
        if k2t = 3 or k2t = 4 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 3 and k2t != 4 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 3 and k1t != 4 then
        if k2t = 3 or k2t = 4 then
          return ceil(k0v*1.2)+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 3 and k2t != 4 then
          return ceil(k0v*1.2)+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    elseif k0t != 3 and k0t != 4 then
      if k1t = 3 or k1t = 4 then
        if k2t = 3 or k2t = 4 then
          return k0v+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 3 and k2t != 4 then
          return k0v+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 3 and k1t != 4 then
        if k2t = 3 or k2t = 4 then
          return k0v+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 3 and k2t != 4 then
          return k0v+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    end if;
  elseif job_type = 3 then /* paladin */
    if k0t = 1 then
      if k1t = 1 then
        if k2t = 1 or k2t = 4 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 1 and k2t != 4 then
          return ceil(k0v*1.2)+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 1 then
        if k2t = 1 or k2t = 4 then
          return ceil(k0v*1.2)+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 1 and k2t != 4 then
          return ceil(k0v*1.2)+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    elseif k0t != 1 then
      if k1t = 1 then
        if k2t = 1 or k2t = 4 then
          return k0v+ceil(k1v*1.2)+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 1 and k2t != 4 then
          return k0v+ceil(k1v*1.2)+k2v+ceil(k3v*1.2);
        end if;
      elseif k1t != 1 then
        if k2t = 1 or k2t = 4 then
          return k0v+k1v+ceil(k2v*1.2)+ceil(k3v*1.2);
        elseif k2t != 1 and k2t != 4 then
          return k0v+k1v+k2v+ceil(k3v*1.2);
        end if;
      end if;
    end if;
  elseif job_type = 4 then
  elseif job_type = 5 then
  end if;
end
$$ language plpgsql;

create or replace function get_max_parameter_value(job_type integer, k0t integer, k0p integer, k1t integer, k1p integer, k2t integer, k2p integer, k3t integer, k3p integer)
  returns integer as $$
declare
  max_val integer;
  p0123 integer;
  p0132 integer;
  p0213 integer;
  p0231 integer;
  p0312 integer;
  p0321 integer;

  p1023 integer;
  p1032 integer;
  p1203 integer;
  p1230 integer;
  p1302 integer;
  p1320 integer;

  p2013 integer;
  p2031 integer;
  p2103 integer;
  p2130 integer;
  p2301 integer;
  p2310 integer;

  p3012 integer;
  p3021 integer;
  p3102 integer;
  p3120 integer;
  p3201 integer;
  p3210 integer;
begin
  max_val = 0;

  p0123 = get_parameter_value(job_type, k0t, k0p, k1t, k1p, k2t, k2p, k3t, k3p);
  if p0123 > max_val then
    max_val = p0123;
  end if;

  p0132 = get_parameter_value(job_type, k0t, k0p, k1t, k1p, k3t, k3p, k2t, k2p);
  if p0132 > max_val then
    max_val = p0132;
  end if;

  p0213 = get_parameter_value(job_type, k0t, k0p, k2t, k2p, k1t, k1p, k3t, k3p);
  if p0123 > max_val then
    max_val = p0213;
  end if;

  p0231 = get_parameter_value(job_type, k0t, k0p, k2t, k2p, k3t, k3p, k1t, k1p);
  if p0231 > max_val then
    max_val = p0231;
  end if;

  p0312 = get_parameter_value(job_type, k0t, k0p, k3t, k3p, k1t, k1p, k2t, k2p);
  if p0312 > max_val then
    max_val = p0312;
  end if;

  p0321 = get_parameter_value(job_type, k0t, k0p, k3t, k3p, k2t, k2p, k1t, k1p);
  if p0321 > max_val then
    max_val = p0321;
  end if;

  p1023 = get_parameter_value(job_type, k1t, k1p, k0t, k0p, k2t, k2p, k3t, k3p);
  if p1023 > max_val then
    max_val = p1023;
  end if;

  p1032 = get_parameter_value(job_type, k1t, k1p, k0t, k0p, k3t, k3p, k2t, k2p);
  if p1032 > max_val then
    max_val = p1032;
  end if;

  p1203 = get_parameter_value(job_type, k1t, k1p, k2t, k2p, k0t, k0p, k3t, k3p);
  if p1203 > max_val then
    max_val = p1203;
  end if;

  p1230 = get_parameter_value(job_type, k1t, k1p, k2t, k2p, k3t, k3p, k0t, k0p);
  if p1230 > max_val then
    max_val = p1230;
  end if;

  p1302 = get_parameter_value(job_type, k1t, k1p, k3t, k3p, k0t, k0p, k2t, k2p);
  if p1302 > max_val then
    max_val = p1302;
  end if;

  p1320 = get_parameter_value(job_type, k1t, k1p, k3t, k3p, k2t, k2p, k0t, k0p);
  if p1320 > max_val then
    max_val = p1320;
  end if;

  p2013 = get_parameter_value(job_type, k2t, k2p, k0t, k0p, k1t, k1p, k3t, k3p);
  if p2013 > max_val then
    max_val = p2013;
  end if;

  p2031 = get_parameter_value(job_type, k2t, k2p, k0t, k0p, k3t, k3p, k1t, k1p);
  if p2031 > max_val then
    max_val = p2031;
  end if;

  p2103 = get_parameter_value(job_type, k2t, k2p, k1t, k1p, k0t, k0p, k3t, k3p);
  if p2103 > max_val then
    max_val = p2103;
  end if;

  p2130 = get_parameter_value(job_type, k2t, k2p, k1t, k1p, k3t, k3p, k0t, k0p);
  if p2130 > max_val then
    max_val = p2130;
  end if;

  p2301 = get_parameter_value(job_type, k2t, k2p, k3t, k3p, k0t, k0p, k1t, k1p);
  if p2301 > max_val then
    max_val = p2301;
  end if;

  p2310 = get_parameter_value(job_type, k2t, k2p, k3t, k3p, k1t, k1p, k0t, k0p);
  if p2310 > max_val then
    max_val = p2310;
  end if;

  p3012 = get_parameter_value(job_type, k3t, k3p, k0t, k0p, k1t, k1p, k2t, k2p);
  if p3012 > max_val then
    max_val = p3012;
  end if;

  p3021 = get_parameter_value(job_type, k3t, k3p, k0t, k0p, k2t, k2p, k1t, k1p);
  if p3021 > max_val then
    max_val = p3021;
  end if;

  p3102 = get_parameter_value(job_type, k3t, k3p, k1t, k1p, k0t, k0p, k2t, k2p);
  if p3102 > max_val then
    max_val = p3102;
  end if;

  p3120 = get_parameter_value(job_type, k3t, k3p, k1t, k1p, k2t, k2p, k0t, k0p);
  if p3120 > max_val then
    max_val = p3120;
  end if;

  p3201 = get_parameter_value(job_type, k3t, k3p, k2t, k2p, k0t, k0p, k1t, k1p);
  if p3201 > max_val then
    max_val = p3201;
  end if;

  p3210 = get_parameter_value(job_type, k3t, k3p, k2t, k2p, k1t, k1p, k0t, k0p);
  if p3210 > max_val then
    max_val = p3210;
  end if;

  return max_val;
end
$$ language plpgsql;

create or replace function get_max_parameter_value_pattern(job_type integer, k0t integer, k0p integer, k1t integer, k1p integer, k2t integer, k2p integer, k3t integer, k3p integer)
  returns text as $$
declare
  max_val integer;
  max_pattern text;
  p0123 integer;
  p0132 integer;
  p0213 integer;
  p0231 integer;
  p0312 integer;
  p0321 integer;

  p1023 integer;
  p1032 integer;
  p1203 integer;
  p1230 integer;
  p1302 integer;
  p1320 integer;

  p2013 integer;
  p2031 integer;
  p2103 integer;
  p2130 integer;
  p2301 integer;
  p2310 integer;

  p3012 integer;
  p3021 integer;
  p3102 integer;
  p3120 integer;
  p3201 integer;
  p3210 integer;
begin

  max_val = 0;
  max_pattern = 'na';

  p0123 = get_parameter_value(job_type, k0t, k0p, k1t, k1p, k2t, k2p, k3t, k3p);
  if p0123 > max_val then
    max_val = p0123;
    max_pattern = 'p0123';
  end if;

  p0132 = get_parameter_value(job_type, k0t, k0p, k1t, k1p, k3t, k3p, k2t, k2p);
  if p0132 > max_val then
    max_val = p0132;
    max_pattern = 'p0132';
  end if;

  p0213 = get_parameter_value(job_type, k0t, k0p, k2t, k2p, k1t, k1p, k3t, k3p);
  if p0213 > max_val then
    max_val = p0213;
    max_pattern = 'p0213';
  end if;

  p0231 = get_parameter_value(job_type, k0t, k0p, k2t, k2p, k3t, k3p, k1t, k1p);
  if p0231 > max_val then
    max_val = p0231;
    max_pattern = 'p0231';
  end if;

  p0312 = get_parameter_value(job_type, k0t, k0p, k3t, k3p, k1t, k1p, k2t, k2p);
  if p0312 > max_val then
    max_val = p0312;
    max_pattern = 'p0312';
  end if;

  p0321 = get_parameter_value(job_type, k0t, k0p, k3t, k3p, k2t, k2p, k1t, k1p);
  if p0321 > max_val then
    max_val = p0321;
    max_pattern = 'p0321';
  end if;

  p1023 = get_parameter_value(job_type, k1t, k1p, k0t, k0p, k2t, k2p, k3t, k3p);
  if p1023 > max_val then
    max_val = p1023;
    max_pattern = 'p1023';
  end if;

  p1032 = get_parameter_value(job_type, k1t, k1p, k0t, k0p, k3t, k3p, k2t, k2p);
  if p1032 > max_val then
    max_val = p1032;
    max_pattern = 'p1032';
  end if;

  p1203 = get_parameter_value(job_type, k1t, k1p, k2t, k2p, k0t, k0p, k3t, k3p);
  if p1203 > max_val then
    max_val = p1203;
    max_pattern = 'p1203';
  end if;

  p1230 = get_parameter_value(job_type, k1t, k1p, k2t, k2p, k3t, k3p, k0t, k0p);
  if p1230 > max_val then
    max_val = p1230;
    max_pattern = 'p1230';
  end if;

  p1302 = get_parameter_value(job_type, k1t, k1p, k3t, k3p, k0t, k0p, k2t, k2p);
  if p1302 > max_val then
    max_val = p1302;
    max_pattern = 'p1302';
  end if;

  p1320 = get_parameter_value(job_type, k1t, k1p, k3t, k3p, k2t, k2p, k0t, k0p);
  if p1320 > max_val then
    max_val = p1320;
    max_pattern = 'p1320';
  end if;

  p2013 = get_parameter_value(job_type, k2t, k2p, k0t, k0p, k1t, k1p, k3t, k3p);
  if p2013 > max_val then
    max_val = p2013;
    max_pattern = 'p2013';
  end if;

  p2031 = get_parameter_value(job_type, k2t, k2p, k0t, k0p, k3t, k3p, k1t, k1p);
  if p2031 > max_val then
    max_val = p2031;
    max_pattern = 'p2031';
  end if;

  p2103 = get_parameter_value(job_type, k2t, k2p, k1t, k1p, k0t, k0p, k3t, k3p);
  if p2103 > max_val then
    max_val = p2103;
    max_pattern = 'p2103';
  end if;

  p2130 = get_parameter_value(job_type, k2t, k2p, k1t, k1p, k3t, k3p, k0t, k0p);
  if p2130 > max_val then
    max_val = p2130;
    max_pattern = 'p2130';
  end if;

  p2301 = get_parameter_value(job_type, k2t, k2p, k3t, k3p, k0t, k0p, k1t, k1p);
  if p2301 > max_val then
    max_val = p2301;
    max_pattern = 'p2301';
  end if;

  p2310 = get_parameter_value(job_type, k2t, k2p, k3t, k3p, k1t, k1p, k0t, k0p);
  if p2310 > max_val then
    max_val = p2310;
    max_pattern = 'p2310';
  end if;

  p3012 = get_parameter_value(job_type, k3t, k3p, k0t, k0p, k1t, k1p, k2t, k2p);
  if p3012 > max_val then
    max_val = p3012;
    max_pattern = 'p3012';
  end if;

  p3021 = get_parameter_value(job_type, k3t, k3p, k0t, k0p, k2t, k2p, k1t, k1p);
  if p3021 > max_val then
    max_val = p3021;
    max_pattern = 'p3021';
  end if;

  p3102 = get_parameter_value(job_type, k3t, k3p, k1t, k1p, k0t, k0p, k2t, k2p);
  if p3102 > max_val then
    max_val = p3102;
    max_pattern = 'p3102';
  end if;

  p3120 = get_parameter_value(job_type, k3t, k3p, k1t, k1p, k2t, k2p, k0t, k0p);
  if p3120 > max_val then
    max_val = p3120;
    max_pattern = 'p3120';
  end if;

  p3201 = get_parameter_value(job_type, k3t, k3p, k2t, k2p, k0t, k0p, k1t, k1p);
  if p3201 > max_val then
    max_val = p3201;
    max_pattern = 'p3201';
  end if;

  p3210 = get_parameter_value(job_type, k3t, k3p, k2t, k2p, k1t, k1p, k0t, k0p);
  if p3210 > max_val then
    max_val = p3210;
    max_pattern = 'p3210';
  end if;

  return max_pattern;
end
$$ language plpgsql;
```
8h, 9GB, (smallint or integer)
```
create materialized view kokoro_flat_cb_mv as /*  */
select
/* ---------------- BATTLE_MASTER ---------------- */
max_battle_master_op_pattern
,max_battle_master_opdx_pattern

/* battle_master slash */
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) battle_master_bagi_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_dein_mag/100.0)) * (1+(dein_mag/100.0))) battle_master_dein_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) battle_master_doruma_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_gira_mag/100.0)) * (1+(gira_mag/100.0))) battle_master_gira_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) battle_master_hyado_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_io_mag/100.0)) * (1+(io_mag/100.0))) battle_master_io_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) battle_master_jibaria_slash_damage
,ceil(max_battle_master_op * (1+(slash_mag/100.0)) * (1+(slash_mera_mag/100.0)) * (1+(mera_mag/100.0))) battle_master_mera_slash_damage

/* battle_master hit */
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) battle_master_bagi_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_dein_mag/100.0)) * (1+(dein_mag/100.0))) battle_master_dein_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) battle_master_doruma_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_gira_mag/100.0)) * (1+(gira_mag/100.0))) battle_master_gira_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) battle_master_hyado_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_io_mag/100.0)) * (1+(io_mag/100.0))) battle_master_io_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) battle_master_jibaria_hit_damage
,ceil(max_battle_master_op * (1+(hit_mag/100.0)) * (1+(hit_mera_mag/100.0)) * (1+(mera_mag/100.0))) battle_master_mera_hit_damage

/* battle_master breath */
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) battle_master_bagi_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_dein_mag/100.0)) * (1+(dein_mag/100.0))) battle_master_dein_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) battle_master_doruma_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_gira_mag/100.0)) * (1+(gira_mag/100.0))) battle_master_gira_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) battle_master_hyado_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_io_mag/100.0)) * (1+(io_mag/100.0))) battle_master_io_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) battle_master_jibaria_breath_damage
,ceil(max_battle_master_opdx * (1+(breath_mag/100.0)) * (1+(breath_mera_mag/100.0)) * (1+(mera_mag/100.0))) battle_master_mera_breath_damage

/* ---------------- RANGER ---------------- */
,max_ranger_op_pattern
,max_ranger_opdx_pattern

/* ranger slash */
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) ranger_bagi_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_dein_mag/100.0)) * (1+(dein_mag/100.0))) ranger_dein_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) ranger_doruma_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_gira_mag/100.0)) * (1+(gira_mag/100.0))) ranger_gira_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) ranger_hyado_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_io_mag/100.0)) * (1+(io_mag/100.0))) ranger_io_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) ranger_jibaria_slash_damage
,ceil(max_ranger_op * (1+(slash_mag/100.0)) * (1+(slash_mera_mag/100.0)) * (1+(mera_mag/100.0))) ranger_mera_slash_damage

/* ranger hit */
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) ranger_bagi_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_dein_mag/100.0)) * (1+(dein_mag/100.0))) ranger_dein_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) ranger_doruma_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_gira_mag/100.0)) * (1+(gira_mag/100.0))) ranger_gira_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) ranger_hyado_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_io_mag/100.0)) * (1+(io_mag/100.0))) ranger_io_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) ranger_jibaria_hit_damage
,ceil(max_ranger_op * (1+(hit_mag/100.0)) * (1+(hit_mera_mag/100.0)) * (1+(mera_mag/100.0))) ranger_mera_hit_damage

/* ranger breath */
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) ranger_bagi_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_dein_mag/100.0)) * (1+(dein_mag/100.0))) ranger_dein_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) ranger_doruma_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_gira_mag/100.0)) * (1+(gira_mag/100.0))) ranger_gira_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) ranger_hyado_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_io_mag/100.0)) * (1+(io_mag/100.0))) ranger_io_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) ranger_jibaria_breath_damage
,ceil(max_ranger_opdx * (1+(breath_mag/100.0)) * (1+(breath_mera_mag/100.0)) * (1+(mera_mag/100.0))) ranger_mera_breath_damage

/* ---------------- SAGE ---------------- */
/* sage os */
,max_sage_os_pattern
,max_sage_ds_pattern

/* sage spell */
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) sage_bagi_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_dein_mag/100.0)) * (1+(dein_mag/100.0))) sage_dein_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) sage_doruma_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_gira_mag/100.0)) * (1+(gira_mag/100.0))) sage_gira_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) sage_hyado_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_io_mag/100.0)) * (1+(io_mag/100.0))) sage_io_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) sage_jibaria_spell_damage
,ceil(max_sage_os * (1+(spell_mag/100.0)) * (1+(spell_mera_mag/100.0)) * (1+(mera_mag/100.0))) sage_mera_spell_damage

/* sage healing */
,ceil(max_sage_ds * (1+(healing_skill_mag/100.0)) * (1+(healing_specialty_mag/100.0))) sage_specialty_healing
,ceil(max_sage_ds * (1+(healing_skill_mag/100.0)) * (1+(healing_spell_mag/100.0))) sage_spell_healing

/* ---------------- PALADIN ---------------- */
/* paladin op */
,max_paladin_op_pattern
,max_paladin_opdx_pattern

/* paladin slash */
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) paladin_bagi_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_dein_mag/100.0)) * (1+(dein_mag/100.0))) paladin_dein_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) paladin_doruma_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_gira_mag/100.0)) * (1+(gira_mag/100.0))) paladin_gira_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) paladin_hyado_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_io_mag/100.0)) * (1+(io_mag/100.0))) paladin_io_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) paladin_jibaria_slash_damage
,ceil(max_paladin_op * (1+(slash_mag/100.0)) * (1+(slash_mera_mag/100.0)) * (1+(mera_mag/100.0))) paladin_mera_slash_damage

/* paladin hit */
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) paladin_bagi_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_dein_mag/100.0)) * (1+(dein_mag/100.0))) paladin_dein_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) paladin_doruma_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_gira_mag/100.0)) * (1+(gira_mag/100.0))) paladin_gira_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) paladin_hyado_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_io_mag/100.0)) * (1+(io_mag/100.0))) paladin_io_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) paladin_jibaria_hit_damage
,ceil(max_paladin_op * (1+(hit_mag/100.0)) * (1+(hit_mera_mag/100.0)) * (1+(mera_mag/100.0))) paladin_mera_hit_damage

/* paladin breath */
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_bagi_mag/100.0)) * (1+(bagi_mag/100.0))) paladin_bagi_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_dein_mag/100.0)) * (1+(dein_mag/100.0))) paladin_dein_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_doruma_mag/100.0)) * (1+(doruma_mag/100.0))) paladin_doruma_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_gira_mag/100.0)) * (1+(gira_mag/100.0))) paladin_gira_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_hyado_mag/100.0)) * (1+(hyado_mag/100.0))) paladin_hyado_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_io_mag/100.0)) * (1+(io_mag/100.0))) paladin_io_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_jibaria_mag/100.0)) * (1+(jibaria_mag/100.0))) paladin_jibaria_breath_damage
,ceil(max_paladin_opdx * (1+(breath_mag/100.0)) * (1+(breath_mera_mag/100.0)) * (1+(mera_mag/100.0))) paladin_mera_breath_damage

,k0name,k1name,k2name,k3name
,total_cost
,k0id,k1id,k2id,k3id,k0grade,k1grade,k2grade,k3grade

from (
select
/* ---------------- BATTLE_MASTER --------------- */

/* battle master op */
get_max_parameter_value(0, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_battle_master_op
,get_max_parameter_value_pattern(0, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_battle_master_op_pattern

/* battle master op, dx */
,get_max_parameter_value(0, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_battle_master_opdx
,get_max_parameter_value_pattern(0, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_battle_master_opdx_pattern

/* battle master op, os */
,get_max_parameter_value(0, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_battle_master_opos
,get_max_parameter_value_pattern(0, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_battle_master_opos_pattern

/* battle master os */
,get_max_parameter_value(0, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_battle_master_os
,get_max_parameter_value_pattern(0, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_battle_master_os_pattern

/* battle master ds */
,get_max_parameter_value(0, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_battle_master_ds
,get_max_parameter_value_pattern(0, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_battle_master_ds_pattern

/* ---------------- RANGER --------------- */

/* ranger op */
,get_max_parameter_value(1, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_ranger_op
,get_max_parameter_value_pattern(1, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_ranger_op_pattern

/* ranger op, dx */
,get_max_parameter_value(1, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_ranger_opdx
,get_max_parameter_value_pattern(1, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_ranger_opdx_pattern

/* ranger op, os */
,get_max_parameter_value(1, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_ranger_opos
,get_max_parameter_value_pattern(1, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_ranger_opos_pattern

/* ranger os */
,get_max_parameter_value(1, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_ranger_os
,get_max_parameter_value_pattern(1, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_ranger_os_pattern

/* ranger ds */
,get_max_parameter_value(1, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_ranger_ds
,get_max_parameter_value_pattern(1, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_ranger_ds_pattern

/* ---------------- SAGE --------------- */

/* sage op */
,get_max_parameter_value(2, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_sage_op
,get_max_parameter_value_pattern(2, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_sage_op_pattern

/* sage op, dx */
,get_max_parameter_value(2, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_sage_opdx
,get_max_parameter_value_pattern(2, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_sage_opdx_pattern

/* sage op, os */
,get_max_parameter_value(2, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_sage_opos
,get_max_parameter_value_pattern(2, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_sage_opos_pattern

/* sage os */
,get_max_parameter_value(2, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_sage_os
,get_max_parameter_value_pattern(2, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_sage_os_pattern

/* sage ds */
,get_max_parameter_value(2, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_sage_ds
,get_max_parameter_value_pattern(2, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_sage_ds_pattern

/* ---------------- PALADIN --------------- */

/* paladin op */
,get_max_parameter_value(3, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_paladin_op
,get_max_parameter_value_pattern(3, k0.type, k0.op, k1.type, k1.op, k2.type, k2.op, k3.type, k3.op) max_paladin_op_pattern

/* paladin op, dx */
,get_max_parameter_value(3, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_paladin_opdx
,get_max_parameter_value_pattern(3, k0.type, k0.op+k0.dx, k1.type, k1.op+k1.dx, k2.type, k2.op+k2.dx, k3.type, k3.op+k3.dx) max_paladin_opdx_pattern

/* paladin op, os */
,get_max_parameter_value(3, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_paladin_opos
,get_max_parameter_value_pattern(3, k0.type, k0.op+k0.os, k1.type, k1.op+k1.os, k2.type, k2.op+k2.os, k3.type, k3.op+k3.os) max_paladin_opos_pattern

/* paladin os */
,get_max_parameter_value(3, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_paladin_os
,get_max_parameter_value_pattern(3, k0.type, k0.os, k1.type, k1.os, k2.type, k2.os, k3.type, k3.os) max_paladin_os_pattern

/* paladin ds */
,get_max_parameter_value(3, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_paladin_ds
,get_max_parameter_value_pattern(3, k0.type, k0.ds, k1.type, k1.ds, k2.type, k2.ds, k3.type, k3.ds) max_paladin_ds_pattern

/* ---------------- MAG ATTACK --------------- */

,(k0.slash_none_none+k1.slash_none_none+k2.slash_none_none+k3.slash_none_none) slash_mag
,(k0.hit_none_none+k1.hit_none_none+k2.hit_none_none+k3.hit_none_none) hit_mag
,(k0.spell_none_none+k1.spell_none_none+k2.spell_none_none+k3.spell_none_none) spell_mag
,(k0.breath_none_none+k1.breath_none_none+k2.breath_none_none+k3.breath_none_none) breath_mag

/* ---------------- MAG ATTRIBUTE --------------- */

,(k0.slash_bagi_none+k1.slash_bagi_none+k2.slash_bagi_none+k3.slash_bagi_none) slash_bagi_mag
,(k0.slash_dein_none+k1.slash_dein_none+k2.slash_dein_none+k3.slash_dein_none) slash_dein_mag
,(k0.slash_doruma_none+k1.slash_doruma_none+k2.slash_doruma_none+k3.slash_doruma_none) slash_doruma_mag
,(k0.slash_gira_none+k1.slash_gira_none+k2.slash_gira_none+k3.slash_gira_none) slash_gira_mag
,(k0.slash_hyado_none+k1.slash_hyado_none+k2.slash_hyado_none+k3.slash_hyado_none) slash_hyado_mag
,(k0.slash_io_none+k1.slash_io_none+k2.slash_io_none+k3.slash_io_none) slash_io_mag
,(k0.slash_jibaria_none+k1.slash_jibaria_none+k2.slash_jibaria_none+k3.slash_jibaria_none) slash_jibaria_mag
,(k0.slash_mera_none+k1.slash_mera_none+k2.slash_mera_none+k3.slash_mera_none) slash_mera_mag

,(k0.hit_bagi_none+k1.hit_bagi_none+k2.hit_bagi_none+k3.hit_bagi_none) hit_bagi_mag
,(k0.hit_dein_none+k1.hit_dein_none+k2.hit_dein_none+k3.hit_dein_none) hit_dein_mag
,(k0.hit_doruma_none+k1.hit_doruma_none+k2.hit_doruma_none+k3.hit_doruma_none) hit_doruma_mag
,(k0.hit_gira_none+k1.hit_gira_none+k2.hit_gira_none+k3.hit_gira_none) hit_gira_mag
,(k0.hit_hyado_none+k1.hit_hyado_none+k2.hit_hyado_none+k3.hit_hyado_none) hit_hyado_mag
,(k0.hit_io_none+k1.hit_io_none+k2.hit_io_none+k3.hit_io_none) hit_io_mag
,(k0.hit_jibaria_none+k1.hit_jibaria_none+k2.hit_jibaria_none+k3.hit_jibaria_none) hit_jibaria_mag
,(k0.hit_mera_none+k1.hit_mera_none+k2.hit_mera_none+k3.hit_mera_none) hit_mera_mag

,(k0.spell_bagi_none+k1.spell_bagi_none+k2.spell_bagi_none+k3.spell_bagi_none) spell_bagi_mag
,(k0.spell_dein_none+k1.spell_dein_none+k2.spell_dein_none+k3.spell_dein_none) spell_dein_mag
,(k0.spell_doruma_none+k1.spell_doruma_none+k2.spell_doruma_none+k3.spell_doruma_none) spell_doruma_mag
,(k0.spell_gira_none+k1.spell_gira_none+k2.spell_gira_none+k3.spell_gira_none) spell_gira_mag
,(k0.spell_hyado_none+k1.spell_hyado_none+k2.spell_hyado_none+k3.spell_hyado_none) spell_hyado_mag
,(k0.spell_io_none+k1.spell_io_none+k2.spell_io_none+k3.spell_io_none) spell_io_mag
,(k0.spell_jibaria_none+k1.spell_jibaria_none+k2.spell_jibaria_none+k3.spell_jibaria_none) spell_jibaria_mag
,(k0.spell_mera_none+k1.spell_mera_none+k2.spell_mera_none+k3.spell_mera_none) spell_mera_mag

,(k0.breath_bagi_none+k1.breath_bagi_none+k2.breath_bagi_none+k3.breath_bagi_none) breath_bagi_mag
,(k0.breath_dein_none+k1.breath_dein_none+k2.breath_dein_none+k3.breath_dein_none) breath_dein_mag
,(k0.breath_doruma_none+k1.breath_doruma_none+k2.breath_doruma_none+k3.breath_doruma_none) breath_doruma_mag
,(k0.breath_gira_none+k1.breath_gira_none+k2.breath_gira_none+k3.breath_gira_none) breath_gira_mag
,(k0.breath_hyado_none+k1.breath_hyado_none+k2.breath_hyado_none+k3.breath_hyado_none) breath_hyado_mag
,(k0.breath_io_none+k1.breath_io_none+k2.breath_io_none+k3.breath_io_none) breath_io_mag
,(k0.breath_jibaria_none+k1.breath_jibaria_none+k2.breath_jibaria_none+k3.breath_jibaria_none) breath_jibaria_mag
,(k0.breath_mera_none+k1.breath_mera_none+k2.breath_mera_none+k3.breath_mera_none) breath_mera_mag

,(k0.all_bagi_none+k1.all_bagi_none+k2.all_bagi_none+k3.all_bagi_none) bagi_mag
,(k0.all_dein_none+k1.all_dein_none+k2.all_dein_none+k3.all_dein_none) dein_mag
,(k0.all_doruma_none+k1.all_doruma_none+k2.all_doruma_none+k3.all_doruma_none) doruma_mag
,(k0.all_gira_none+k1.all_gira_none+k2.all_gira_none+k3.all_gira_none) gira_mag
,(k0.all_hyado_none+k1.all_hyado_none+k2.all_hyado_none+k3.all_hyado_none) hyado_mag
,(k0.all_io_none+k1.all_io_none+k2.all_io_none+k3.all_io_none) io_mag
,(k0.all_jibaria_none+k1.all_jibaria_none+k2.all_jibaria_none+k3.all_jibaria_none) jibaria_mag
,(k0.all_mera_none+k1.all_mera_none+k2.all_mera_none+k3.all_mera_none) mera_mag

/* healing */
,(k0.healing_skill+k1.healing_skill+k2.healing_skill+k3.healing_skill) healing_skill_mag
,(k0.healing_specialty+k1.healing_specialty+k2.healing_specialty+k3.healing_specialty) healing_specialty_mag
,(k0.healing_spell+k1.healing_spell+k2.healing_spell+k3.healing_spell) healing_spell_mag

/* ---------------- MAG RACE ---------------- */

,(k0.all_none_animal+k1.all_none_animal+k2.all_none_animal+k3.all_none_animal) animal_mag
,(k0.all_none_bird+k1.all_none_bird+k2.all_none_bird+k3.all_none_bird) bird_mag
,(k0.all_none_devil+k1.all_none_devil+k2.all_none_devil+k3.all_none_devil) devil_mag
,(k0.all_none_dragon+k1.all_none_dragon+k2.all_none_dragon+k3.all_none_dragon) dragon_mag
,(k0.all_none_element+k1.all_none_element+k2.all_none_element+k3.all_none_element) element_mag
,(k0.all_none_insect+k1.all_none_insect+k2.all_none_insect+k3.all_none_insect) insect_mag
,(k0.all_none_machine+k1.all_none_machine+k2.all_none_machine+k3.all_none_machine) machine_mag
,(k0.all_none_material+k1.all_none_material+k2.all_none_material+k3.all_none_material) material_mag
,(k0.all_none_phantom+k1.all_none_phantom+k2.all_none_phantom+k3.all_none_phantom) phantom_mag
,(k0.all_none_plant+k1.all_none_plant+k2.all_none_plant+k3.all_none_plant) plant_mag
,(k0.all_none_slime+k1.all_none_slime+k2.all_none_slime+k3.all_none_slime) slime_mag
,(k0.all_none_water+k1.all_none_water+k2.all_none_water+k3.all_none_water) water_mag
,(k0.all_none_zombie+k1.all_none_zombie+k2.all_none_zombie+k3.all_none_zombie) zombie_mag

/* ---------------- PARAMETER ---------------- */

,k0.uuid k0uuid, k0.id k0id, k0.name k0name, k0.grade k0grade, k0.type k0type, k0.hp k0hp, k0.mp k0mp, k0.op k0op, k0.dp k0dp, k0.os k0os, k0.ds k0ds, k0.dx k0dx, k0.sp k0sp, k0.cost k0cost, k0.plus_cost k0pcost
,k0.breath_none_none k0_breath_none_none, k0.hit_none_none k0_hit_none_none, k0.slash_none_none k0_slash_none_none, k0.spell_none_none k0_spell_none_none
,k0.all_bagi_none k0_all_bagi_none, k0.all_dein_none k0_all_dein_none, k0.all_doruma_none k0_all_doruma_none, k0.all_gira_none k0_all_gira_none, k0.all_hyado_none k0_all_hyado_none, k0.all_io_none k0_all_io_none, k0.all_jibaria_none k0_all_jibaria_none, k0.all_mera_none k0_all_mera_none
,k0.hit_bagi_none k0_hit_bagi_none, k0.hit_dein_none k0_hit_dein_none, k0.hit_doruma_none k0_hit_doruma_none, k0.hit_gira_none k0_hit_gira_none, k0.hit_hyado_none k0_hit_hyado_none, k0.hit_io_none k0_hit_io_none, k0.hit_jibaria_none k0_hit_jibaria_none, k0.hit_mera_none k0_hit_mera_none
,k0.slash_bagi_none k0_slash_bagi_none, k0.slash_dein_none k0_slash_dein_none, k0.slash_doruma_none k0_slash_doruma_none, k0.slash_gira_none k0_slash_gira_none, k0.slash_hyado_none k0_slash_hyado_none, k0.slash_io_none k0_slash_io_none, k0.slash_jibaria_none k0_slash_jibaria_none, k0.slash_mera_none k0_slash_mera_none
,k0.spell_bagi_none k0_spell_bagi_none, k0.spell_dein_none k0_spell_dein_none, k0.spell_doruma_none k0_spell_doruma_none, k0.spell_gira_none k0_spell_gira_none, k0.spell_hyado_none k0_spell_hyado_none, k0.spell_io_none k0_spell_io_none, k0.spell_jibaria_none k0_spell_jibaria_none, k0.spell_mera_none k0_spell_mera_none
,k0.all_none_animal k0_all_none_animal, k0.all_none_bird k0_all_none_bird, k0.all_none_devil k0_all_none_devil, k0.all_none_dragon k0_all_none_dragon, k0.all_none_element k0_all_none_element, k0.all_none_insect k0_all_none_insect, k0.all_none_machine k0_all_none_machine, k0.all_none_material k0_all_none_material, k0.all_none_phantom k0_all_none_phantom, k0.all_none_plant k0_all_none_plant, k0.all_none_slime k0_all_none_slime, k0.all_none_water k0_all_none_water, k0.all_none_zombie k0_all_none_zombie

,k1.uuid k1uuid, k1.id k1id, k1.name k1name, k1.grade k1grade, k1.type k1type, k1.hp k1hp, k1.mp k1mp, k1.op k1op, k1.dp k1dp, k1.os k1os, k1.ds k1ds, k1.dx k1dx, k1.sp k1sp, k1.cost k1cost, k1.plus_cost k1pcost
,k1.breath_none_none k1_breath_none_none, k1.hit_none_none k1_hit_none_none, k1.slash_none_none k1_slash_none_none, k1.spell_none_none k1_spell_none_none
,k1.all_bagi_none k1_all_bagi_none, k1.all_dein_none k1_all_dein_none, k1.all_doruma_none k1_all_doruma_none, k1.all_gira_none k1_all_gira_none, k1.all_hyado_none k1_all_hyado_none, k1.all_io_none k1_all_io_none, k1.all_jibaria_none k1_all_jibaria_none, k1.all_mera_none k1_all_mera_none
,k1.hit_bagi_none k1_hit_bagi_none, k1.hit_dein_none k1_hit_dein_none, k1.hit_doruma_none k1_hit_doruma_none, k1.hit_gira_none k1_hit_gira_none, k1.hit_hyado_none k1_hit_hyado_none, k1.hit_io_none k1_hit_io_none, k1.hit_jibaria_none k1_hit_jibaria_none, k1.hit_mera_none k1_hit_mera_none
,k1.slash_bagi_none k1_slash_bagi_none, k1.slash_dein_none k1_slash_dein_none, k1.slash_doruma_none k1_slash_doruma_none, k1.slash_gira_none k1_slash_gira_none, k1.slash_hyado_none k1_slash_hyado_none, k1.slash_io_none k1_slash_io_none, k1.slash_jibaria_none k1_slash_jibaria_none, k1.slash_mera_none k1_slash_mera_none
,k1.spell_bagi_none k1_spell_bagi_none, k1.spell_dein_none k1_spell_dein_none, k1.spell_doruma_none k1_spell_doruma_none, k1.spell_gira_none k1_spell_gira_none, k1.spell_hyado_none k1_spell_hyado_none, k1.spell_io_none k1_spell_io_none, k1.spell_jibaria_none k1_spell_jibaria_none, k1.spell_mera_none k1_spell_mera_none
,k1.all_none_animal k1_all_none_animal, k1.all_none_bird k1_all_none_bird, k1.all_none_devil k1_all_none_devil, k1.all_none_dragon k1_all_none_dragon, k1.all_none_element k1_all_none_element, k1.all_none_insect k1_all_none_insect, k1.all_none_machine k1_all_none_machine, k1.all_none_material k1_all_none_material, k1.all_none_phantom k1_all_none_phantom, k1.all_none_plant k1_all_none_plant, k1.all_none_slime k1_all_none_slime, k1.all_none_water k1_all_none_water, k1.all_none_zombie k1_all_none_zombie

,k2.uuid k2uuid, k2.id k2id, k2.name k2name, k2.grade k2grade, k2.type k2type, k2.hp k2hp, k2.mp k2mp, k2.op k2op, k2.dp k2dp, k2.os k2os, k2.ds k2ds, k2.dx k2dx, k2.sp k2sp, k2.cost k2cost, k2.plus_cost k2pcost
,k2.breath_none_none k2_breath_none_none, k2.hit_none_none k2_hit_none_none, k2.slash_none_none k2_slash_none_none, k2.spell_none_none k2_spell_none_none
,k2.all_bagi_none k2_all_bagi_none, k2.all_dein_none k2_all_dein_none, k2.all_doruma_none k2_all_doruma_none, k2.all_gira_none k2_all_gira_none, k2.all_hyado_none k2_all_hyado_none, k2.all_io_none k2_all_io_none, k2.all_jibaria_none k2_all_jibaria_none, k2.all_mera_none k2_all_mera_none
,k2.hit_bagi_none k2_hit_bagi_none, k2.hit_dein_none k2_hit_dein_none, k2.hit_doruma_none k2_hit_doruma_none, k2.hit_gira_none k2_hit_gira_none, k2.hit_hyado_none k2_hit_hyado_none, k2.hit_io_none k2_hit_io_none, k2.hit_jibaria_none k2_hit_jibaria_none, k2.hit_mera_none k2_hit_mera_none
,k2.slash_bagi_none k2_slash_bagi_none, k2.slash_dein_none k2_slash_dein_none, k2.slash_doruma_none k2_slash_doruma_none, k2.slash_gira_none k2_slash_gira_none, k2.slash_hyado_none k2_slash_hyado_none, k2.slash_io_none k2_slash_io_none, k2.slash_jibaria_none k2_slash_jibaria_none, k2.slash_mera_none k2_slash_mera_none
,k2.spell_bagi_none k2_spell_bagi_none, k2.spell_dein_none k2_spell_dein_none, k2.spell_doruma_none k2_spell_doruma_none, k2.spell_gira_none k2_spell_gira_none, k2.spell_hyado_none k2_spell_hyado_none, k2.spell_io_none k2_spell_io_none, k2.spell_jibaria_none k2_spell_jibaria_none, k2.spell_mera_none k2_spell_mera_none
,k2.all_none_animal k2_all_none_animal, k2.all_none_bird k2_all_none_bird, k2.all_none_devil k2_all_none_devil, k2.all_none_dragon k2_all_none_dragon, k2.all_none_element k2_all_none_element, k2.all_none_insect k2_all_none_insect, k2.all_none_machine k2_all_none_machine, k2.all_none_material k2_all_none_material, k2.all_none_phantom k2_all_none_phantom, k2.all_none_plant k2_all_none_plant, k2.all_none_slime k2_all_none_slime, k2.all_none_water k2_all_none_water, k2.all_none_zombie k2_all_none_zombie

,k3.uuid k3uuid, k3.id k3id, k3.name k3name, k3.grade k3grade, k3.type k3type, k3.hp k3hp, k3.mp k3mp, k3.op k3op, k3.dp k3dp, k3.os k3os, k3.ds k3ds, k3.dx k3dx, k3.sp k3sp, k3.cost k3cost, k3.plus_cost k3pcost
,k3.breath_none_none k3_breath_none_none, k3.hit_none_none k3_hit_none_none, k3.slash_none_none k3_slash_none_none, k3.spell_none_none k3_spell_none_none
,k3.all_bagi_none k3_all_bagi_none, k3.all_dein_none k3_all_dein_none, k3.all_doruma_none k3_all_doruma_none, k3.all_gira_none k3_all_gira_none, k3.all_hyado_none k3_all_hyado_none, k3.all_io_none k3_all_io_none, k3.all_jibaria_none k3_all_jibaria_none, k3.all_mera_none k3_all_mera_none
,k3.hit_bagi_none k3_hit_bagi_none, k3.hit_dein_none k3_hit_dein_none, k3.hit_doruma_none k3_hit_doruma_none, k3.hit_gira_none k3_hit_gira_none, k3.hit_hyado_none k3_hit_hyado_none, k3.hit_io_none k3_hit_io_none, k3.hit_jibaria_none k3_hit_jibaria_none, k3.hit_mera_none k3_hit_mera_none
,k3.slash_bagi_none k3_slash_bagi_none, k3.slash_dein_none k3_slash_dein_none, k3.slash_doruma_none k3_slash_doruma_none, k3.slash_gira_none k3_slash_gira_none, k3.slash_hyado_none k3_slash_hyado_none, k3.slash_io_none k3_slash_io_none, k3.slash_jibaria_none k3_slash_jibaria_none, k3.slash_mera_none k3_slash_mera_none
,k3.spell_bagi_none k3_spell_bagi_none, k3.spell_dein_none k3_spell_dein_none, k3.spell_doruma_none k3_spell_doruma_none, k3.spell_gira_none k3_spell_gira_none, k3.spell_hyado_none k3_spell_hyado_none, k3.spell_io_none k3_spell_io_none, k3.spell_jibaria_none k3_spell_jibaria_none, k3.spell_mera_none k3_spell_mera_none
,k3.all_none_animal k3_all_none_animal, k3.all_none_bird k3_all_none_bird, k3.all_none_devil k3_all_none_devil, k3.all_none_dragon k3_all_none_dragon, k3.all_none_element k3_all_none_element, k3.all_none_insect k3_all_none_insect, k3.all_none_machine k3_all_none_machine, k3.all_none_material k3_all_none_material, k3.all_none_phantom k3_all_none_phantom, k3.all_none_plant k3_all_none_plant, k3.all_none_slime k3_all_none_slime, k3.all_none_water k3_all_none_water, k3.all_none_zombie k3_all_none_zombie

,k0.cost + k1.cost + k2.cost + k3.cost - k0.plus_cost - k1.plus_cost - k2.plus_cost - k3.plus_cost total_cost

from kokoro_flat_entity k0 cross join kokoro_flat_entity k1 cross join kokoro_flat_entity k2 cross join kokoro_flat_entity k3
where k0.name < k1.name and k0.name < k2.name and k0.name < k3.name and k1.name < k2.name and k1.name < k3.name and k2.name < k3.name
) cb
;


create index on kokoro_flat_cb_mv(battle_master_bagi_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_dein_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_doruma_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_gira_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_hyado_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_io_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_jibaria_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_mera_slash_damage);
create index on kokoro_flat_cb_mv(battle_master_bagi_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_dein_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_doruma_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_gira_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_hyado_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_io_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_jibaria_hit_damage);
create index on kokoro_flat_cb_mv(battle_master_mera_hit_damage);
create index on kokoro_flat_cb_mv(ranger_bagi_slash_damage);
create index on kokoro_flat_cb_mv(ranger_dein_slash_damage);
create index on kokoro_flat_cb_mv(ranger_doruma_slash_damage);
create index on kokoro_flat_cb_mv(ranger_gira_slash_damage);
create index on kokoro_flat_cb_mv(ranger_hyado_slash_damage);
create index on kokoro_flat_cb_mv(ranger_io_slash_damage);
create index on kokoro_flat_cb_mv(ranger_jibaria_slash_damage);
create index on kokoro_flat_cb_mv(ranger_mera_slash_damage);
create index on kokoro_flat_cb_mv(ranger_bagi_hit_damage);
create index on kokoro_flat_cb_mv(ranger_dein_hit_damage);
create index on kokoro_flat_cb_mv(ranger_doruma_hit_damage);
create index on kokoro_flat_cb_mv(ranger_gira_hit_damage);
create index on kokoro_flat_cb_mv(ranger_hyado_hit_damage);
create index on kokoro_flat_cb_mv(ranger_io_hit_damage);
create index on kokoro_flat_cb_mv(ranger_jibaria_hit_damage);
create index on kokoro_flat_cb_mv(ranger_mera_hit_damage);
create index on kokoro_flat_cb_mv(ranger_bagi_breath_damage);
create index on kokoro_flat_cb_mv(ranger_dein_breath_damage);
create index on kokoro_flat_cb_mv(ranger_doruma_breath_damage);
create index on kokoro_flat_cb_mv(ranger_gira_breath_damage);
create index on kokoro_flat_cb_mv(ranger_hyado_breath_damage);
create index on kokoro_flat_cb_mv(ranger_io_breath_damage);
create index on kokoro_flat_cb_mv(ranger_jibaria_breath_damage);
create index on kokoro_flat_cb_mv(ranger_mera_breath_damage);
create index on kokoro_flat_cb_mv(sage_bagi_spell_damage);
create index on kokoro_flat_cb_mv(sage_dein_spell_damage);
create index on kokoro_flat_cb_mv(sage_doruma_spell_damage);
create index on kokoro_flat_cb_mv(sage_gira_spell_damage);
create index on kokoro_flat_cb_mv(sage_hyado_spell_damage);
create index on kokoro_flat_cb_mv(sage_io_spell_damage);
create index on kokoro_flat_cb_mv(sage_jibaria_spell_damage);
create index on kokoro_flat_cb_mv(sage_mera_spell_damage);
create index on kokoro_flat_cb_mv(paladin_bagi_slash_damage);
create index on kokoro_flat_cb_mv(paladin_dein_slash_damage);
create index on kokoro_flat_cb_mv(paladin_doruma_slash_damage);
create index on kokoro_flat_cb_mv(paladin_gira_slash_damage);
create index on kokoro_flat_cb_mv(paladin_hyado_slash_damage);
create index on kokoro_flat_cb_mv(paladin_io_slash_damage);
create index on kokoro_flat_cb_mv(paladin_jibaria_slash_damage);
create index on kokoro_flat_cb_mv(paladin_mera_slash_damage);
create index on kokoro_flat_cb_mv(sage_specialty_healing);
create index on kokoro_flat_cb_mv(sage_spell_healing);
```