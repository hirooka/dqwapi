package dqwapi.api.v1;

import dqwapi.domain.model.weapon.Weapon;
import dqwapi.domain.service.IWeaponService;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("${dqwapi.path-prefix}v1/weapons")
@RestController
public class WeaponRestController {

  private final IWeaponService weaponService;

  @Hidden
  @GetMapping("w")
  public List<Map<String, String>> getWeapons() {
    return weaponService.getWeapons();
  }

  @Hidden
  @GetMapping("s")
  public List<Map<String, String>> getSkills(@RequestParam(value = "w") final String weapon) {
    return weaponService.getSkills(weapon);
  }

  @Hidden
  @GetMapping
  public List<Weapon> getAll() {
    return weaponService.getAll();
  }

}
