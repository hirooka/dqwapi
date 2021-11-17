```
copy dev.public.kokoro_combination from 's3://dqwapi/combination.csv' 
credentials 'aws_access_key_id=xxx;aws_secret_access_key=xxx'
csv;

copy dev.public.kokoro_flat_entity from 's3://dqwapi/kokoro-flat-nd.json' 
credentials 'aws_access_key_id=xxx;aws_secret_access_key=xxx'
format as json 'auto';

select k0id, k0grade, k1id, k1grade, k2id, k2grade, k3id, k3grade, max_battle_master_op_pattern 
from kokoro_combination 
where total_cost < 900
and k0id not in (100, 200) and k1id not in (100, 200) and k2id not in (100, 200) and k3id not in (100, 200)
and concat(k0id, concat('_', k0grade)) not in ('404_1', '100b') and concat(k1id, concat('_', k1grade)) not in ('100a', '100b') 
order by battle_master_bagi_slash_damage desc limit 50

select *
from kokoro_flat_entity k0
cross join kokoro_flat_entity k1
cross join kokoro_flat_entity k2
cross join kokoro_flat_entity k3
where k0.name < k1.name and k0.name < k2.name and k0.name < k3.name and k1.name < k2.name and k1.name < k3.name and k2.name < k3.name
order by k0.hp
limit 100
```