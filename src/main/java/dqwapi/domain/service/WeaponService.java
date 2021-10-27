package dqwapi.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.weapon.Skill;
import dqwapi.domain.model.weapon.Weapon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeaponService implements IWeaponService {

  private List<Weapon> weapons = new ArrayList<>();

  @PostConstruct
  void init() {
    final String weaponJson = "weapon.json";
    final Resource weaponJsonResource = new ClassPathResource(weaponJson);
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      weapons =
          objectMapper.readValue(weaponJsonResource.getInputStream(), new TypeReference<>() {});
      log.info("{} weapons", weapons.size());
      log.debug(weapons.toString());
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to parse JSON file.", ex);
    }
  }

  @Override
  public List<Map<String, String>> getWeapons() {
    final List<Map<String, String>> list = new ArrayList<>();
    for (Weapon weapon : weapons) {
      final Map<String, String> map = new HashMap<>();
      map.put("text", weapon.getName());
      map.put("value", weapon.getName());
      list.add(map);
    }
    return list;
  }

  @Override
  public List<Map<String, String>> getSkills(final String weaponName) {
    final List<Map<String, String>> list = new ArrayList<>();
    for (Weapon weapon : weapons) {
      if (weapon.getName().equals(weaponName)) {
        for (Skill skill : weapon.getSkills()) {
          final Map<String, String> map = new HashMap<>();
          map.put("text", skill.getName());
          map.put("value", skill.getName());
          list.add(map);
        }
      }
    }
    return list;
  }

  @Override
  public List<Map<String, Object>> get() {
    final List<Map<String, Object>> list1 = new ArrayList<>();
    for (Weapon weapon : weapons) {
      final Map<String, Object> weaponMap = new HashMap<>();
      final List<Map<String, String>> list2 = new ArrayList<>();
      for (Skill skill : weapon.getSkills()) {
        final Map<String, String> skillMap = new HashMap<>();
        skillMap.put("text", skill.getName());
        skillMap.put("value", skill.getName());
        list2.add(skillMap);
      }
      weaponMap.put("text", weapon.getName());
      weaponMap.put("value", list2);
      list1.add(weaponMap);
    }
    return list1;
  }

  @Override
  public List<Weapon> getAll() {
    return weapons;
  }
}
