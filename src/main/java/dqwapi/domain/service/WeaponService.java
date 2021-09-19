package dqwapi.domain.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dqwapi.domain.model.weapon.Weapon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
  public List<Weapon> getAll() {
    return weapons;
  }
}
