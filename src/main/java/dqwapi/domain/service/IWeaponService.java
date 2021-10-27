package dqwapi.domain.service;

import dqwapi.domain.model.weapon.Weapon;
import java.util.List;
import java.util.Map;

public interface IWeaponService {
  List<Map<String, String>> getWeapons();

  List<Map<String, String>> getSkills(final String weapon);

  List<Map<String, Object>> get();

  List<Weapon> getAll();
}
