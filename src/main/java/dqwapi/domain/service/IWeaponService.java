package dqwapi.domain.service;

import dqwapi.domain.model.weapon.Weapon;
import java.util.List;

public interface IWeaponService {
  List<Weapon> getAll();
}
