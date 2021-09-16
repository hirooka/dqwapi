package dqwapi.api.v1;

import dqwapi.domain.model.weapon.Weapon;
import dqwapi.domain.service.IWeaponService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/weapons")
@RestController
public class WeaponRestController {

  private final IWeaponService weaponService;

  @GetMapping
  public List<Weapon> getAll() {
    return weaponService.getAll();
  }

}
